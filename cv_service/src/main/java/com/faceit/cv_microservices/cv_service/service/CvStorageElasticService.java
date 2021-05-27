package com.faceit.cv_microservices.cv_service.service;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CvStorageElasticService {

    Page<CvResponse> findAllCv(Pageable pageable);
}
