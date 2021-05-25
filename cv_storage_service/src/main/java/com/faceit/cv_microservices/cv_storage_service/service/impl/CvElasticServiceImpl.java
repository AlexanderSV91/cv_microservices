package com.faceit.cv_microservices.cv_storage_service.service.impl;

import com.faceit.cv_microservices.cv_storage_service.model.elastic.CvElastic;
import com.faceit.cv_microservices.cv_storage_service.repository.elastic.CvElasticRepository;
import com.faceit.cv_microservices.cv_storage_service.service.CvElasticService;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CvElasticServiceImpl implements CvElasticService {

    private final ElasticsearchRestTemplate elasticRestTemplate;
    private final CvElasticRepository cvElasticRepository;

    public CvElasticServiceImpl(ElasticsearchRestTemplate elasticRestTemplate,
                                CvElasticRepository cvElasticRepository) {
        this.elasticRestTemplate = elasticRestTemplate;
        this.cvElasticRepository = cvElasticRepository;
    }

    @Override
    public void saveAll(List<CvElastic> cvElasticList) {
        this.cvElasticRepository.saveAll(cvElasticList);
    }

    @Override
    public List<CvElastic> findAll() {
        return StreamSupport
                .stream(this.cvElasticRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
