package com.faceit.cv_microservices.cv_storage_service.service;

import com.faceit.cv_microservices.cv_storage_service.model.mongo.CvMongo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CvMongoService {

    void saveAll(List<CvMongo> cvMongoList);

    Page<CvMongo> findAll(Pageable pageable);
}
