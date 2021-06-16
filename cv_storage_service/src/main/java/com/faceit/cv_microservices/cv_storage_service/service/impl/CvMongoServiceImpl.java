package com.faceit.cv_microservices.cv_storage_service.service.impl;

import com.faceit.cv_microservices.cv_storage_service.model.mongo.CvMongo;
import com.faceit.cv_microservices.cv_storage_service.repository.mongo.CvMongoRepository;
import com.faceit.cv_microservices.cv_storage_service.service.CvMongoService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void saveAll(List<CvMongo> cvMongoList) {
        this.cvMongoRepository.saveAll(cvMongoList);
    }

    @Cacheable(value = "cvMongoListCache", key = "#root.targetClass.simpleName+'.'+#root.methodName+'?pn='+#pageable.pageNumber+'&ps='+#pageable.pageSize+'&sort='+#pageable.sort.toString()")
    @Override
    public Page<CvMongo> findAll(Pageable pageable) {
        return this.cvMongoRepository.findAll(pageable);
    }

    @Cacheable(value = "cvMongoCache", key = "#root.targetClass.simpleName+'.'+#root.methodName+'?id='+#id")
    @Override
    public CvMongo findById(String id) {
        return this.cvMongoRepository.findById(id).orElseThrow();
    }
}
