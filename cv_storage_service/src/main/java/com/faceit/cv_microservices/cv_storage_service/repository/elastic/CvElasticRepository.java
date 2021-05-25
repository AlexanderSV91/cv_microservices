package com.faceit.cv_microservices.cv_storage_service.repository.elastic;

import com.faceit.cv_microservices.cv_storage_service.model.elastic.CvElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CvElasticRepository extends ElasticsearchRepository<CvElastic, String> {
}
