package com.faceit.cv_microservices.cv_storage_service.repository.elastic;

import com.faceit.cv_microservices.cv_storage_service.dto.request.CvRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CvElasticRepository extends ElasticsearchRepository<CvRequest, String> {
}
