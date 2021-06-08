package com.faceit.cv_microservices.search_google_service.controller;

import com.faceit.cv_microservices.search_google_service.dto.request.CvRequest;
import com.faceit.cv_microservices.search_google_service.model.Reference;
import com.faceit.cv_microservices.search_google_service.service.SearchService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Log4j2
@RestController
@RequestMapping("search")
public class SearchControllerRest {

    private final SearchService searchService;

    public SearchControllerRest(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public List<Reference> search(@RequestBody CvRequest cvRequest) throws ExecutionException, InterruptedException {
        if (Objects.isNull(cvRequest.getUser())
                || (StringUtils.isBlank(cvRequest.getUser().getFirstName()) && (StringUtils.isBlank(cvRequest.getUser().getLastName())))) {
            throw new RuntimeException();
        }
        return searchService.search(cvRequest).get();
    }
}
