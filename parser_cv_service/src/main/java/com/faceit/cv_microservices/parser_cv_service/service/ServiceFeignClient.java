package com.faceit.cv_microservices.parser_cv_service.service;

import com.faceit.cv_microservices.parser_cv_service.model.CvModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "cv-storage")
public interface ServiceFeignClient {

    @PostMapping("/cv")
    void saveCvBulk(@RequestBody final List<CvModel> cvModelList);
}
