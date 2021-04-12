package com.faceit.cv_microservices.cv_storage_service.service;

import com.faceit.cv_microservices.cv_storage_service.model.elastic.CvElasticModel;

import java.util.List;

public interface CvElasticService {

    void saveAll(final List<CvElasticModel> cvElasticModelList);

    List<CvElasticModel> findAll();
}
