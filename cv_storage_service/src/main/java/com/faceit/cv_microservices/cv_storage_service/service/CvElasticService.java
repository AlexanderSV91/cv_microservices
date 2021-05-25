package com.faceit.cv_microservices.cv_storage_service.service;

import com.faceit.cv_microservices.cv_storage_service.model.elastic.CvElastic;

import java.util.List;

public interface CvElasticService {

    void saveAll(final List<CvElastic> cvElasticList);

    List<CvElastic> findAll();
}
