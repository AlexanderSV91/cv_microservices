package com.faceit.cv_microservices.cv_storage_service.controller;

import com.faceit.cv_microservices.cv_storage_service.model.elastic.CvElastic;
import com.faceit.cv_microservices.cv_storage_service.service.CvElasticService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/elastic")
public class CvElasticControllerRest {

    private final CvElasticService cvElasticService;

    public CvElasticControllerRest(CvElasticService cvElasticService) {
        this.cvElasticService = cvElasticService;
    }

    @PostMapping("/cv")
    public void saveCvBulk(@RequestBody List<CvElastic> cvList) {
        this.cvElasticService.saveAll(cvList);
    }

    @GetMapping("/cv")
    public Page<CvElastic> findAllCv(Pageable pageable) {
        return this.cvElasticService.findAll(pageable);
    }
}
