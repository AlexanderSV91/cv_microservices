package com.faceit.cv_microservices.cv_storage_service.service.impl;

import com.faceit.cv_microservices.cv_storage_service.model.elastic.CvElasticModel;
import com.faceit.cv_microservices.cv_storage_service.repository.elastic.CvElasticRepository;
import com.faceit.cv_microservices.cv_storage_service.service.CvElasticService;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CvElasticServiceImpl implements CvElasticService {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final CvElasticRepository cvElasticRepository;

    public CvElasticServiceImpl(ElasticsearchRestTemplate elasticsearchRestTemplate,
                                CvElasticRepository cvElasticRepository) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.cvElasticRepository = cvElasticRepository;
    }

    @Override
    public void saveAll(List<CvElasticModel> cvElasticModelList) {
        cvElasticRepository.saveAll(cvElasticModelList);
    }

    @Override
    public List<CvElasticModel> findAll() {
        return StreamSupport
                .stream(cvElasticRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
