package com.faceit.cv_microservices.cv_service.service.impl;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import com.faceit.cv_microservices.cv_service.mapper.CvElasticMapper;
import com.faceit.cv_microservices.cv_service.model.elastic.CvElastic;
import com.faceit.cv_microservices.cv_service.service.CvStorageElasticService;
import com.faceit.cv_microservices.cv_service.service.CvStorageServiceFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CvStorageElasticServiceImpl implements CvStorageElasticService {

    private final CvStorageServiceFeignClient cvStorageServiceFeignClient;
    private final CvElasticMapper cvElasticMapper;

    public CvStorageElasticServiceImpl(CvStorageServiceFeignClient cvStorageServiceFeignClient,
                                       CvElasticMapper cvElasticMapper) {
        this.cvStorageServiceFeignClient = cvStorageServiceFeignClient;
        this.cvElasticMapper = cvElasticMapper;
    }

    @Override
    public Page<CvResponse> findAllCv(Pageable pageable) {
        Page<CvElastic> cvPage = this.cvStorageServiceFeignClient.findAllCvElastic(pageable);
        return pageEntityToPageResponse(cvPage);
    }

    private PageImpl<CvResponse> pageEntityToPageResponse(Page<CvElastic> page) {
        return new PageImpl<>(this.cvElasticMapper.toCvResponseList(page.getContent()), page.getPageable(), page.getTotalElements());
    }
}
