package com.faceit.cv_microservices.cv_service.service;

import com.faceit.cv_microservices.cv_service.model.elastic.CvElastic;
import com.faceit.cv_microservices.cv_service.model.mongo.CvMongo;

import java.util.List;

public interface CvStorageService {

    List<CvElastic> findAllCvElastic();

    List<CvMongo> findAllCvMongo();
}
