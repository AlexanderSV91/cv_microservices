package com.faceit.cv_microservices.cv_storage_service.controller;

import com.faceit.cv_microservices.cv_storage_service.model.elastic.CvElastic;
import com.faceit.cv_microservices.cv_storage_service.service.CvElasticService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elastic")
public class CvElasticControllerRest {

    private final CvElasticService cvElasticService;

    public CvElasticControllerRest(CvElasticService cvElasticService) {
        this.cvElasticService = cvElasticService;
    }

    @PostMapping("/cv")
    public void saveCvBulk(@RequestBody final List<CvElastic> cvList) {
        this.cvElasticService.saveAll(cvList);
    }

    @GetMapping("/cv")
    public List<CvElastic> findAllCvElastic() {
        return this.cvElasticService.findAll();
    }
}
