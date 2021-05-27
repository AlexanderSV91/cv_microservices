package com.faceit.cv_microservices.cv_service.service;


import com.faceit.cv_microservices.cv_service.model.elastic.CvElastic;
import com.faceit.cv_microservices.cv_service.model.mongo.CvMongo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "cv-storage-service")
public interface CvStorageServiceFeignClient {

    @GetMapping("/elastic/cv")
    Page<CvElastic> findAllCvElastic(Pageable pageable);

    @GetMapping("/mongo/cv")
    Page<CvMongo> findAllCvMongo(Pageable pageable);
}
