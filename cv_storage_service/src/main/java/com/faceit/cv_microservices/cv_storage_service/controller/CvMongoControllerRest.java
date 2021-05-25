package com.faceit.cv_microservices.cv_storage_service.controller;

import com.faceit.cv_microservices.cv_storage_service.model.mongo.CvMongo;
import com.faceit.cv_microservices.cv_storage_service.service.CvMongoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongo")
public class CvMongoControllerRest {

    private final CvMongoService cvMongoService;

    public CvMongoControllerRest(CvMongoService cvMongoService) {
        this.cvMongoService = cvMongoService;
    }

    @PostMapping("/cv")
    public void saveCvBulk(@RequestBody final List<CvMongo> cvList) {
        this.cvMongoService.saveAll(cvList);
    }

    @GetMapping("/cv")
    public List<CvMongo> findAllCvMongo() {
        return this.cvMongoService.findAll();
    }
}
