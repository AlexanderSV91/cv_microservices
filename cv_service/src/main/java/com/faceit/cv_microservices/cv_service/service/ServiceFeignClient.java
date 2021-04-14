package com.faceit.cv_microservices.cv_service.service;


import com.faceit.cv_microservices.cv_service.model.elastic.CvElasticModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "cv-storage-service")
public interface ServiceFeignClient {

    @GetMapping("/elastic")
    List<CvElasticModel> findAllCvElastic();
}
