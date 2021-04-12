package com.faceit.cv_microservices.cv_storage_service.service.impl;

import com.faceit.cv_microservices.cv_storage_service.model.mongo.CvMongoModel;
import com.faceit.cv_microservices.cv_storage_service.repository.mongo.CvMongoRepository;
import com.faceit.cv_microservices.cv_storage_service.service.CvMongoService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvMongoServiceImpl implements CvMongoService {

    private final MongoTemplate mongoTemplate;
    private final CvMongoRepository cvMongoRepository;

    public CvMongoServiceImpl(MongoTemplate mongoTemplate, CvMongoRepository cvMongoRepository) {
        this.mongoTemplate = mongoTemplate;
        this.cvMongoRepository = cvMongoRepository;
    }

    @Override
    public void saveAll(List<CvMongoModel> cvMongoModelList) {
        cvMongoRepository.saveAll(cvMongoModelList);
    }
}