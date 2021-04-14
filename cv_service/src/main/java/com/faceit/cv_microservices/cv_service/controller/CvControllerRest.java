package com.faceit.cv_microservices.cv_service.controller;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import com.faceit.cv_microservices.cv_service.mapper.CvElasticMapper;
import com.faceit.cv_microservices.cv_service.model.elastic.CvElasticModel;
import com.faceit.cv_microservices.cv_service.service.CvStorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class CvControllerRest {

    private final CvStorageService cvStorageService;
    private final CvElasticMapper cvElasticMapper;

    public CvControllerRest(CvStorageService cvStorageService,
                            CvElasticMapper cvElasticMapper) {
        this.cvStorageService = cvStorageService;
        this.cvElasticMapper = cvElasticMapper;
    }

    @GetMapping("/elastic")
    public ResponseEntity<List<CvResponse>> findAllCvElastic() {
        List<CvElasticModel> cvElasticModelList = cvStorageService.findAllCvElastic();
        List<CvResponse> cvResponseList = cvElasticMapper.toCvResponseList(cvElasticModelList);
        return ResponseEntity.status(HttpStatus.OK).body(cvResponseList);
    }
}
