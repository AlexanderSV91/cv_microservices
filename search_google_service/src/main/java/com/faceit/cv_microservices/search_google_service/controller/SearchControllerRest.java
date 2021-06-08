package com.faceit.cv_microservices.search_google_service.controller;

import com.faceit.cv_microservices.search_google_service.model.Reference;
import com.faceit.cv_microservices.search_google_service.service.SearchService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Log4j2
@RestController
@RequestMapping("search")
public class SearchControllerRest {

    private final SearchService searchService;

    public SearchControllerRest(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/{text}")
    public List<Reference> search(@PathVariable String text) throws ExecutionException, InterruptedException {
        if (StringUtils.isBlank(text)) {
            throw new RuntimeException();
        }
        return searchService.search(text).get();
    }
}
