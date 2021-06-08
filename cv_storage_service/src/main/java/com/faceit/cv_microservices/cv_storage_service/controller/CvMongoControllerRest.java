package com.faceit.cv_microservices.cv_storage_service.controller;

import com.faceit.cv_microservices.cv_storage_service.model.mongo.CvMongo;
import com.faceit.cv_microservices.cv_storage_service.service.CvMongoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/mongo")
public class CvMongoControllerRest {

    private final CvMongoService cvMongoService;

    public CvMongoControllerRest(CvMongoService cvMongoService) {
        this.cvMongoService = cvMongoService;
    }

    @PostMapping("/cv")
    public void saveCvBulk(@RequestBody List<CvMongo> cvList) {
        this.cvMongoService.saveAll(cvList);
    }

    @GetMapping("/cv")
    public Page<CvMongo> findAllCv(Pageable pageable) {
        return this.cvMongoService.findAll(pageable);
    }

    @GetMapping("/cv/{id}")
    public CvMongo findById(@PathVariable String id) {
        return this.cvMongoService.findById(id);
    }
}
