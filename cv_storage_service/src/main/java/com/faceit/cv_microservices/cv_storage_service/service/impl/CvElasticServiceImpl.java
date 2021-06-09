package com.faceit.cv_microservices.cv_storage_service.service.impl;

import com.faceit.cv_microservices.cv_storage_service.model.elastic.CvElastic;
import com.faceit.cv_microservices.cv_storage_service.repository.elastic.CvElasticRepository;
import com.faceit.cv_microservices.cv_storage_service.service.CvElasticService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Page<CvElastic> findAll(Pageable pageable) {
        return this.cvElasticRepository.findAll(pageable);
    }

    @Override
    public CvElastic findById(String id) {
        return this.cvElasticRepository.findById(id).orElseThrow();
    }
}
