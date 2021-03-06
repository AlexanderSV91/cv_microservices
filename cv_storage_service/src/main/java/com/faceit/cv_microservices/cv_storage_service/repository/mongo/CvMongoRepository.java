package com.faceit.cv_microservices.cv_storage_service.repository.mongo;

import com.faceit.cv_microservices.cv_storage_service.model.mongo.CvMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CvMongoRepository extends MongoRepository<CvMongo, String> {
}
