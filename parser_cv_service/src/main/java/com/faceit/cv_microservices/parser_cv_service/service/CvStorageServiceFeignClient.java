package com.faceit.cv_microservices.parser_cv_service.service;

import com.faceit.cv_microservices.parser_cv_service.model.Cv;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "cv-storage-service")
public interface CvStorageServiceFeignClient {

    @PostMapping("/elastic/cv")
    void saveElasticCvBulk(@RequestBody final List<Cv> cvList);

    @PostMapping("/mongo/cv")
    void saveMongoCvBulk(@RequestBody final List<Cv> cvList);
}
