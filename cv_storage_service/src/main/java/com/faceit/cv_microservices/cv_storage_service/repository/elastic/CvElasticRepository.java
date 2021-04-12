package com.faceit.cv_microservices.cv_storage_service.repository.elastic;

import com.faceit.cv_microservices.cv_storage_service.model.elastic.CvElasticModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CvElasticRepository extends ElasticsearchRepository<CvElasticModel, String> {
}
