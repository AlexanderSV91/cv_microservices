package com.faceit.cv_microservices.cv_service.controller;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import com.faceit.cv_microservices.cv_service.mapper.CvElasticMapper;
import com.faceit.cv_microservices.cv_service.service.CvStorageElasticService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/v1/elastic")
public class CvElasticControllerRest {

    private final CvStorageElasticService cvStorageElasticService;
    private final CvElasticMapper cvElasticMapper;

    public CvElasticControllerRest(CvStorageElasticService cvStorageElasticService,
                                   CvElasticMapper cvElasticMapper) {
        this.cvStorageElasticService = cvStorageElasticService;
        this.cvElasticMapper = cvElasticMapper;
    }

    @GetMapping
    public ResponseEntity<Page<CvResponse>> findAllCvElastic(Pageable pageable) {
        return ResponseEntity.ok(this.cvStorageElasticService.findAllCv(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CvResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.cvElasticMapper.toCvResponse(this.cvStorageElasticService.findById(id)));
    }
}
