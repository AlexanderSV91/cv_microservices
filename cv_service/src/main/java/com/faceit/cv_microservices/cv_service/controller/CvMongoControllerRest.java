package com.faceit.cv_microservices.cv_service.controller;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import com.faceit.cv_microservices.cv_service.mapper.CvMongoMapper;
import com.faceit.cv_microservices.cv_service.service.CvStorageMongoService;
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
@RequestMapping("/api/v1/mongo")
public class CvMongoControllerRest {

    private final CvStorageMongoService cvStorageMongoService;
    private final CvMongoMapper cvMongoMapper;

    public CvMongoControllerRest(CvStorageMongoService cvStorageMongoService,
                                 CvMongoMapper cvMongoMapper) {
        this.cvStorageMongoService = cvStorageMongoService;
        this.cvMongoMapper = cvMongoMapper;
    }

    @GetMapping
    public ResponseEntity<Page<CvResponse>> findAllCv(Pageable pageable) {
        return ResponseEntity.ok(this.cvStorageMongoService.findAllCv(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CvResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.cvMongoMapper.toCvResponse(this.cvStorageMongoService.findById(id)));
    }
}
