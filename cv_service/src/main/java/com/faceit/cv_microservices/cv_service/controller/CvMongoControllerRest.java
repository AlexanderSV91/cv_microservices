package com.faceit.cv_microservices.cv_service.controller;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import com.faceit.cv_microservices.cv_service.service.CvStorageMongoService;
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
@RequestMapping("/api/v1/mongo")
public class CvMongoControllerRest {

    private final CvStorageMongoService cvStorageMongoService;

    public CvMongoControllerRest(CvStorageMongoService cvStorageMongoService) {
        this.cvStorageMongoService = cvStorageMongoService;
    }

    @GetMapping
    public ResponseEntity<Page<CvResponse>> findAllCv(Pageable pageable) {
        Page<CvResponse> cvResponsePage = this.cvStorageMongoService.findAllCv(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(cvResponsePage);
    }
}
