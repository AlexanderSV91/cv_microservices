package com.faceit.cv_microservices.cv_service.controller;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import com.faceit.cv_microservices.cv_service.service.CvStorageElasticService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/v1/elastic")
public class CvElasticControllerRest {

    private final CvStorageElasticService cvStorageElasticService;

    public CvElasticControllerRest(CvStorageElasticService cvStorageElasticService) {
        this.cvStorageElasticService = cvStorageElasticService;
    }

    @GetMapping
    public ResponseEntity<Page<CvResponse>> findAllCvElastic(Pageable pageable) {
        Page<CvResponse> cvResponsePage = this.cvStorageElasticService.findAllCv(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(cvResponsePage);
    }
}
