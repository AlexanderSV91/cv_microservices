package com.faceit.cv_microservices.cv_service.service;


import com.faceit.cv_microservices.cv_service.model.elastic.CvElastic;
import com.faceit.cv_microservices.cv_service.model.mongo.CvMongo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "cv-storage-service")
public interface CvStorageServiceFeignClient {

    @GetMapping("/elastic/cv")
    List<CvElastic> findAllCvElastic();

    @GetMapping("/mongo/cv")
    List<CvMongo> findAllCvMongo();
}
