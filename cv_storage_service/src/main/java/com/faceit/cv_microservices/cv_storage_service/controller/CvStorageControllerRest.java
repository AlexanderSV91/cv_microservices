package com.faceit.cv_microservices.cv_storage_service.controller;

import com.faceit.cv_microservices.cv_storage_service.dto.request.CvRequest;
import com.faceit.cv_microservices.cv_storage_service.mapper.CvMongoMapper;
import com.faceit.cv_microservices.cv_storage_service.model.mongo.CvMongoModel;
import com.faceit.cv_microservices.cv_storage_service.service.CvMongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CvStorageControllerRest {

    private final CvMongoService cvMongoService;
    private final CvMongoMapper cvMongoMapper;

    public CvStorageControllerRest(CvMongoService cvMongoService,
                                   CvMongoMapper cvMongoMapper) {
        this.cvMongoService = cvMongoService;
        this.cvMongoMapper = cvMongoMapper;
    }

    @PostMapping("/cv")
    public void saveCvBulk(@RequestBody final List<CvRequest> cvRequestList) {
        List<CvMongoModel> cvMongoModelList = cvMongoMapper.toCvMongoModelList(cvRequestList);
        cvMongoService.saveAll(cvMongoModelList);
    }
}
