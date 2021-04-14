package com.faceit.cv_microservices.cv_service.service.impl;

import com.faceit.cv_microservices.cv_service.model.elastic.CvElasticModel;
import com.faceit.cv_microservices.cv_service.service.CvStorageService;
import com.faceit.cv_microservices.cv_service.service.ServiceFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class CvStorageServiceImpl implements CvStorageService {

    private final ServiceFeignClient serviceFeignClient;

    public CvStorageServiceImpl(ServiceFeignClient serviceFeignClient) {
        this.serviceFeignClient = serviceFeignClient;
    }

    @Override
    public List<CvElasticModel> findAllCvElastic() {
        return serviceFeignClient.findAllCvElastic();
    }
}
