package com.faceit.cv_microservices.cv_storage_service.service;

import com.faceit.cv_microservices.cv_storage_service.model.elastic.CvElastic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CvElasticService {

    void saveAll(List<CvElastic> cvElasticList);

    Page<CvElastic> findAll(Pageable pageable);

    CvElastic findById(String id);
}
