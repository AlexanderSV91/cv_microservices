package com.faceit.cv_microservices.cv_service.controller;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import com.faceit.cv_microservices.cv_service.mapper.CvMongoMapper;
import com.faceit.cv_microservices.cv_service.model.mongo.CvMongo;
import com.faceit.cv_microservices.cv_service.service.CvStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mongo")
public class CvMongoControllerRest {

    private final CvStorageService cvStorageService;
    private final CvMongoMapper cvMongoMapper;

    public CvMongoControllerRest(CvStorageService cvStorageService,
                                 CvMongoMapper cvMongoMapper) {
        this.cvStorageService = cvStorageService;
        this.cvMongoMapper = cvMongoMapper;
    }

    @GetMapping
    public ResponseEntity<List<CvResponse>> findAllCvMongo() {
        List<CvMongo> cvMongoList = this.cvStorageService.findAllCvMongo();
        List<CvResponse> cvResponseList = this.cvMongoMapper.toCvResponseList(cvMongoList);
        return ResponseEntity.status(HttpStatus.OK).body(cvResponseList);
    }
}
