package com.faceit.cv_microservices.search_google_service.service.impl;

import com.faceit.cv_microservices.search_google_service.dto.request.CvRequest;
import com.faceit.cv_microservices.search_google_service.model.Reference;
import com.faceit.cv_microservices.search_google_service.service.SearchService;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;

@Log4j2
@Service
public class SearchServiceImpl implements SearchService {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
    private static final String BASE_ADDRESS = "https://www.google.com";
    private final List<Reference> referenceList;
    private int page = 0;

    public SearchServiceImpl() {
        this.referenceList = new ArrayList<>(110);
    }

    @Override
    @Async("threadPoolTaskExecutorParsing")
    public Future<List<Reference>> search(CvRequest cv) {
        return this.search(cv.getUser().getFirstName() + " " + cv.getUser().getLastName());
    }

    private Future<List<Reference>> search(String text) {
        while (9 >= this.page) {
            Document doc = null;
            try {
                doc = Jsoup
                        .connect(BASE_ADDRESS + "/search?q=" + text + "&hl=ru&start=" + (this.page * 10))
                        .userAgent(USER_AGENT)
                        .timeout(5_000)
                        .get();
            } catch (IOException e) {
                log.error("crashed method parsingFirstPage() message=" + e.getMessage());
            }
            if (Objects.isNull(doc)) {
                throw new RuntimeException();
            }
            Elements elementsByClass = doc.getElementsByClass("ZINbbc xpd O9g5cc uUPGi");
            elementsByClass.forEach(element -> this.referenceList.add(new Reference(
                    element.getElementsByAttribute("href").attr("href").replace("/url?q=", ""),
                    element.getElementsByClass("BNeawe vvjwJb AP7Wnd").text(),
                    element.getElementsByClass("BNeawe s3v9rd AP7Wnd").text())));
            this.page++;
        }
        return AsyncResult.forValue(this.referenceList);
    }
}
