package com.faceit.cv_microservices.parser_cv_service.service.impl;

import com.faceit.cv_microservices.parser_cv_service.model.CvModel;
import com.faceit.cv_microservices.parser_cv_service.model.PreviousWork;
import com.faceit.cv_microservices.parser_cv_service.model.Salary;
import com.faceit.cv_microservices.parser_cv_service.model.User;
import com.faceit.cv_microservices.parser_cv_service.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SchedulerServiceImpl implements SchedulerService {

    private static final int FIXED_DELAY = 10_000;

    @Override
    @Async("threadPoolTaskExecutorParsing")
    @Scheduled(fixedDelay = FIXED_DELAY)
    public void parsingFirstPage() {
        Document doc = null;
        try {
            doc = Jsoup
                    .connect("https://www.work.ua/resumes-zaporizhzhya/?page=1")
                    .userAgent("Mozilla")
                    .timeout(5_000)
                    .get();
        } catch (IOException e) {
            log.error("crashed method parsingFirstPage() message=" + e.getMessage());
        }
        if (null != doc) {
            Elements elements = doc.getElementsByClass("card card-hover resume-link card-visited wordwrap");
            List<CvModel> cvModelList = new ArrayList<>(elements.size());
            elements.forEach(element -> {
                CvModel cvModel = new CvModel();
                User user = new User();
                Salary salary = new Salary();
                List<PreviousWork> previousWorkList = new ArrayList<>();

                Elements h2 = element.select("h2");
                h2.forEach(elementH2 -> {
                    cvModel.setTitleCv(element.select("h2").select("a").text());
                    String href = element.select("h2").select("a").attr("href");
                    cvModel.setId(Long.parseLong(href.replace("resumes","").replace("/","")));
                    cvModel.setHrefCv("https://www.work.ua" + href);

                    String rawSalary = elementH2.getElementsByClass("nowrap").text();
                    if (!rawSalary.isEmpty()) {
                        String[] s = rawSalary.split(" ");
                        salary.setValue(Integer.parseInt(s[0]));
                        salary.setCurrencyType(s[1]);
                    }

                    String rawDateCreateCv = element.select("h2").select("a").attr("title");
                    cvModel.setDateCreateCv(rawDateCreateCv.substring(rawDateCreateCv.indexOf("резюме від") + 10).trim());
                });

                String name = element.getElementsByTag("b").text();
                if (!"Приховано".equals(name)) {
                    String[] rawFullName = name.split(" ");
                    if (rawFullName.length == 2) {
                        user.setFirstName(rawFullName[1]);
                        user.setLastName(rawFullName[0]);
                    } else if (rawFullName.length == 1) {
                        user.setFirstName(rawFullName[0]);
                        user.setLastName(rawFullName[0]);
                    }
                }

                String srcImage = element.getElementsByTag("img").attr("src");
                if (!srcImage.isEmpty()) {
                    user.setSrcImage(srcImage);
                }

                Elements elementsByClass = element.getElementsByClass("add-bottom");
                elementsByClass.forEach(elementByClass -> {
                    String[] raw = elementByClass.getElementsByClass("text-muted").first().text().split("·");
                    if (raw.length == 1) {
                        cvModel.setEducation(raw[0].trim());
                    } else if (raw.length == 2) {
                        cvModel.setEducation(raw[0].trim());
                        cvModel.setTypeOfEmployment(raw[1].trim().split(","));
                    }

                    Elements li = elementByClass.getElementsByTag("li");
                    li.forEach(elementLi -> {
                        String[] raws = elementLi.text().trim().split(",");
                        PreviousWork previousWork = new PreviousWork();
                        if (raws.length == 1) {
                            previousWork.setPositionName(raws[0]);
                        } else if (raws.length == 2) {
                            previousWork.setPositionName(raws[0]);
                            String[] strings = raws[1].trim().split("·");
                            previousWork.setCompanyName(strings[0].trim());
                            previousWork.setYear(strings[1].trim());
                        }
                        previousWorkList.add(previousWork);
                    });
                });

                cvModel.setPreviousWorks(previousWorkList);
                cvModel.setSalary(salary);
                cvModel.setUser(user);
                cvModel.setDateTimeParsingCv(LocalDateTime.now());
                cvModelList.add(cvModel);
            });
            System.out.println(cvModelList);
        }
    }

    @Override
    public void parsingNextPage() {

    }
}
