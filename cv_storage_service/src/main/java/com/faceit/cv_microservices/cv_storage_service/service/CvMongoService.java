package com.faceit.cv_microservices.cv_storage_service.service;

import com.faceit.cv_microservices.cv_storage_service.model.mongo.CvMongoModel;

import java.util.List;

public interface CvMongoService {

    void saveAll(final List<CvMongoModel> cvMongoModelList);
}
