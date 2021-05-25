package com.faceit.cv_microservices.cv_service.controller;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import com.faceit.cv_microservices.cv_service.mapper.CvElasticMapper;
import com.faceit.cv_microservices.cv_service.model.elastic.CvElastic;
import com.faceit.cv_microservices.cv_service.service.CvStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/elastic")
public class CvElasticControllerRest {

    private final CvStorageService cvStorageService;
    private final CvElasticMapper cvElasticMapper;

    public CvElasticControllerRest(CvStorageService cvStorageService,
                                   CvElasticMapper cvElasticMapper) {
        this.cvStorageService = cvStorageService;
        this.cvElasticMapper = cvElasticMapper;
    }

    @GetMapping
    public ResponseEntity<List<CvResponse>> findAllCvElastic() {
        List<CvElastic> cvElasticList = this.cvStorageService.findAllCvElastic();
        List<CvResponse> cvResponseList = this.cvElasticMapper.toCvResponseList(cvElasticList);
        return ResponseEntity.status(HttpStatus.OK).body(cvResponseList);
    }
}
