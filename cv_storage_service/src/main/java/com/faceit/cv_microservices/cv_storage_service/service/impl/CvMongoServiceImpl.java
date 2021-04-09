package com.faceit.cv_microservices.cv_storage_service.service.impl;

import com.faceit.cv_microservices.cv_storage_service.model.mongo.CvMongoModel;
import com.faceit.cv_microservices.cv_storage_service.repository.mongo.CvMongoRepository;
import com.faceit.cv_microservices.cv_storage_service.service.CvMongoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvMongoServiceImpl implements CvMongoService {

    private final CvMongoRepository cvMongoRepository;

    public CvMongoServiceImpl(CvMongoRepository cvMongoRepository) {
        this.cvMongoRepository = cvMongoRepository;
    }

    @Override
    public void saveAll(List<CvMongoModel> cvMongoModelList) {
        cvMongoRepository.saveAll(cvMongoModelList);
    }
}
