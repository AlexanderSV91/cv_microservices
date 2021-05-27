package com.faceit.cv_microservices.cv_service.service.impl;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import com.faceit.cv_microservices.cv_service.mapper.CvMongoMapper;
import com.faceit.cv_microservices.cv_service.model.mongo.CvMongo;
import com.faceit.cv_microservices.cv_service.service.CvStorageMongoService;
import com.faceit.cv_microservices.cv_service.service.CvStorageServiceFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CvStorageMongoServiceImpl implements CvStorageMongoService {

    private final CvStorageServiceFeignClient cvStorageServiceFeignClient;
    private final CvMongoMapper cvMongoMapper;

    public CvStorageMongoServiceImpl(CvStorageServiceFeignClient cvStorageServiceFeignClient,
                                     CvMongoMapper cvMongoMapper) {
        this.cvStorageServiceFeignClient = cvStorageServiceFeignClient;
        this.cvMongoMapper = cvMongoMapper;
    }

    @Override
    public Page<CvResponse> findAllCv(Pageable pageable) {
        Page<CvMongo> cvPage = this.cvStorageServiceFeignClient.findAllCvMongo(pageable);
        return pageEntityToPageResponse(cvPage);
    }

    private PageImpl<CvResponse> pageEntityToPageResponse(Page<CvMongo> page) {
        return new PageImpl<>(this.cvMongoMapper.toCvResponseList(page.getContent()), page.getPageable(), page.getTotalElements());
    }
}
