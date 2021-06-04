package com.faceit.cv_microservices.search_google_service.service.impl;

import com.faceit.cv_microservices.search_google_service.service.SearchService;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Log4j2
@Service
public class SearchServiceImpl implements SearchService {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
    private static final String BASE_ADDRESS = "https://www.google.com";

    @Override
    public String search(String text) {
        Document doc = null;
        try {
            doc = Jsoup
                    .connect(BASE_ADDRESS + "/search?q=" + text + "&hl=ru")
                    .userAgent(USER_AGENT)
                    .timeout(5_000)
                    .get();
        } catch (IOException e) {
            log.error("crashed method parsingFirstPage() message=" + e.getMessage());
        }
        if (Objects.isNull(doc)) {
            throw new RuntimeException();
        }
        //log.error(doc.getElementsByAttribute("href").parents());
        //Elements elementsByClass = doc.getElementsByClass("ZINbbc xpd O9g5cc uUPGi");
        Elements elementsByClass = doc.getAllElements();

        log.error(elementsByClass);
        return doc.text();
    }
}
