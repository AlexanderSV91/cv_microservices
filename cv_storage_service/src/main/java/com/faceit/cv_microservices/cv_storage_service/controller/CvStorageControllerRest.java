package com.faceit.cv_microservices.cv_storage_service.controller;

import com.faceit.cv_microservices.cv_storage_service.dto.request.CvRequest;
import com.faceit.cv_microservices.cv_storage_service.mapper.CvElasticMapper;
import com.faceit.cv_microservices.cv_storage_service.mapper.CvMongoMapper;
import com.faceit.cv_microservices.cv_storage_service.model.elastic.CvElasticModel;
import com.faceit.cv_microservices.cv_storage_service.model.mongo.CvMongoModel;
import com.faceit.cv_microservices.cv_storage_service.service.CvElasticService;
import com.faceit.cv_microservices.cv_storage_service.service.CvMongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CvStorageControllerRest {

    private final CvMongoService cvMongoService;
    private final CvMongoMapper cvMongoMapper;
    private final CvElasticService cvElasticService;
    private final CvElasticMapper cvElasticMapper;

    public CvStorageControllerRest(CvMongoService cvMongoService, CvMongoMapper cvMongoMapper,
                                   CvElasticService cvElasticService, CvElasticMapper cvElasticMapper) {
        this.cvMongoService = cvMongoService;
        this.cvMongoMapper = cvMongoMapper;
        this.cvElasticService = cvElasticService;
        this.cvElasticMapper = cvElasticMapper;
    }

    @PostMapping("/cv")
    public void saveCvBulk(@RequestBody final List<CvRequest> cvRequestList) {
        List<CvMongoModel> cvMongoModelList = cvMongoMapper.toCvMongoModelList(cvRequestList);
        List<CvElasticModel> cvElasticModelList = cvElasticMapper.toCvElasticModelList(cvRequestList);
        cvMongoService.saveAll(cvMongoModelList);
        cvElasticService.saveAll(cvElasticModelList);
    }

    @GetMapping("/elastic")
    public List<CvElasticModel> findAllCvElastic() {
        return cvElasticService.findAll();
    }
}
