package com.faceit.cv_microservices.parser_cv_service.service.impl;

import com.faceit.cv_microservices.parser_cv_service.model.Cv;
import com.faceit.cv_microservices.parser_cv_service.model.PreviousWork;
import com.faceit.cv_microservices.parser_cv_service.model.Salary;
import com.faceit.cv_microservices.parser_cv_service.model.User;
import com.faceit.cv_microservices.parser_cv_service.service.CvStorageServiceFeignClient;
import com.faceit.cv_microservices.parser_cv_service.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class SchedulerServiceImpl implements SchedulerService {

    private static final String BASE_ADDRESS = "https://www.work.ua";
    private static final String CITY = "/resumes-zaporizhzhya";

    private static int lastPage;

    private final CvStorageServiceFeignClient cvStorageServiceFeignClient;

    public SchedulerServiceImpl(CvStorageServiceFeignClient cvStorageServiceFeignClient) {
        this.cvStorageServiceFeignClient = cvStorageServiceFeignClient;
    }

    @Override
    @Async("threadPoolTaskExecutorParsing")
    @Scheduled(fixedDelay = 10_000)
    public void parsingFirstPage() {
        List<Cv> cvs = this.parsing(1);
        if (!CollectionUtils.isEmpty(cvs)) {
            this.cvStorageServiceFeignClient.saveMongoCvBulk(cvs);
            this.cvStorageServiceFeignClient.saveElasticCvBulk(cvs);
        }
    }

    @Override
    public void parsingNextPage() {

    }

    private List<Cv> parsing(final int page) {
        Document doc = null;
        try {
            doc = Jsoup
                    .connect(BASE_ADDRESS + CITY + "/?page=" + page)
                    .userAgent("Mozilla")
                    .timeout(5_000)
                    .get();
        } catch (IOException e) {
            log.error("crashed method parsingFirstPage() message=" + e.getMessage());
        }

        if (Objects.nonNull(doc)) {
            final Elements elements = doc.getElementsByClass("card card-hover resume-link card-visited wordwrap");
            List<Cv> cvList = new ArrayList<>();
            elements.forEach(element -> {
                Cv cv = new Cv();
                User user = new User();
                Salary salary = new Salary();
                List<PreviousWork> previousWorkList = new ArrayList<>();

                final Elements h2 = element.select("h2");
                h2.forEach(elementH2 -> {
                    String titleCv = element.select("h2").select("a").text();
                    if (StringUtils.isNotEmpty(titleCv)) {
                        cv.setTitleCv(titleCv);
                    }

                    String href = element.select("h2").select("a").attr("href");
                    if (StringUtils.isNotEmpty(href)) {
                        cv.setId(href.replace("resumes", "").replace("/", ""));
                        cv.setHrefCv(BASE_ADDRESS + href);
                    }

                    String rawSalary = elementH2.getElementsByClass("nowrap").text();
                    if (StringUtils.isNotEmpty(rawSalary)) {
                        String[] s = rawSalary.split(" ");
                        salary.setValue(Integer.parseInt(s[0]));
                        salary.setCurrencyType(s[1]);
                    }
                });

                String name = element.getElementsByTag("b").text();
                if (StringUtils.isNotEmpty(name)) {
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
                }

                String srcImage = element.getElementsByTag("img").attr("src");
                if (StringUtils.isNotEmpty(srcImage)) {
                    user.setSrcImage(srcImage);
                }

                final Elements elementsByClass = element.getElementsByClass("add-bottom");
                elementsByClass.forEach(elementByClass -> {
                    String[] raw = elementByClass.getElementsByClass("text-muted").first().text().split("·");
                    if (raw.length == 1) {
                        cv.setEducation(raw[0].trim());
                    } else if (raw.length == 2) {
                        cv.setEducation(raw[0].trim());
                        cv.setTypeOfEmployments(Arrays.asList(raw[1].trim().split(",")));
                    }

                    final Elements li = elementByClass.getElementsByTag("li");
                    li.forEach(elementLi -> {
                        String[] raws = elementLi.text().trim().split(",");
                        PreviousWork previousWork = new PreviousWork();
                        if (raws.length == 1) {
                            previousWork.setPositionName(raws[0]);
                        } else if (raws.length == 2) {
                            previousWork.setPositionName(raws[0]);
                            String[] strings = raws[1].trim().split("·");
                            if (strings.length == 1) {
                                previousWork.setCompanyName(strings[0].trim());
                                previousWork.setYear(strings[0].trim());
                            } else if (strings.length == 2) {
                                previousWork.setCompanyName(strings[0].trim());
                                previousWork.setYear(strings[1].trim());
                            }
                        }
                        if (Objects.nonNull(previousWork.getPositionName())
                                || Objects.nonNull(previousWork.getCompanyName())
                                || Objects.nonNull(previousWork.getYear())) {
                            previousWorkList.add(previousWork);
                        }
                    });
                });

                if (previousWorkList.size() > 0) {
                    cv.setPreviousWorks(previousWorkList);
                }
                if (Objects.nonNull(salary.getCurrencyType())) {
                    cv.setSalary(salary);
                }
                if (Objects.nonNull(user.getFirstName()) && Objects.nonNull(user.getLastName())) {
                    cv.setUser(user);
                }
                cvList.add(cv);
            });
            return cvList;
        }
        return Collections.emptyList();
    }
}
