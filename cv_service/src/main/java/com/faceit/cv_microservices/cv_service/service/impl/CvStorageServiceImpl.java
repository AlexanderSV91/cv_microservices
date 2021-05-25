package com.faceit.cv_microservices.cv_service.service.impl;

import com.faceit.cv_microservices.cv_service.model.elastic.CvElastic;
import com.faceit.cv_microservices.cv_service.model.mongo.CvMongo;
import com.faceit.cv_microservices.cv_service.service.CvStorageService;
import com.faceit.cv_microservices.cv_service.service.CvStorageServiceFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class CvStorageServiceImpl implements CvStorageService {

    private final CvStorageServiceFeignClient cvStorageServiceFeignClient;

    public CvStorageServiceImpl(CvStorageServiceFeignClient cvStorageServiceFeignClient) {
        this.cvStorageServiceFeignClient = cvStorageServiceFeignClient;
    }

    @Override
    public List<CvElastic> findAllCvElastic() {
        return this.cvStorageServiceFeignClient.findAllCvElastic();
    }

    @Override
    public List<CvMongo> findAllCvMongo() {
        return this.cvStorageServiceFeignClient.findAllCvMongo();
    }
}
