package com.faceit.cv_microservices.cv_service.service;

import com.faceit.cv_microservices.cv_service.model.elastic.CvElasticModel;

import java.util.List;

public interface CvStorageService {

    List<CvElasticModel> findAllCvElastic();
}
