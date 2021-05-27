package com.faceit.cv_microservices.cv_service.service.impl;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import com.faceit.cv_microservices.cv_service.mapper.CvElasticMapper;
import com.faceit.cv_microservices.cv_service.model.elastic.CvElastic;
import com.faceit.cv_microservices.cv_service.model.elastic.PreviousWorkElastic;
import com.faceit.cv_microservices.cv_service.model.elastic.SalaryElastic;
import com.faceit.cv_microservices.cv_service.model.elastic.UserElastic;
import com.faceit.cv_microservices.cv_service.service.CvStorageElasticService;
import com.faceit.cv_microservices.cv_service.service.CvStorageServiceFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    @Retry(name = "cv-elastic-resilience", fallbackMethod = "findAllCvFallback")
    @CircuitBreaker(name = "cv-elastic-resilience")
    @Override
    public Page<CvResponse> findAllCv(Pageable pageable) {
        Page<CvElastic> cvPage = this.cvStorageServiceFeignClient.findAllCvElastic(pageable);
        return pageEntityToPageResponse(cvPage);
    }

    private PageImpl<CvResponse> pageEntityToPageResponse(Page<CvElastic> page) {
        return new PageImpl<>(this.cvElasticMapper.toCvResponseList(page.getContent()), page.getPageable(), page.getTotalElements());
    }

    private Page<CvElastic> findAllCvFallback(final Pageable pageable, final Exception ex) {
        CvElastic cvElastic = new CvElastic(
                "-1",
                "no",
                new SalaryElastic("no", -1),
                new UserElastic("no", "no", "no"),
                Collections.singletonList("no"),
                "no",
                Collections.singletonList(new PreviousWorkElastic("no", "no", "-1")),
                "no");
        List<CvElastic> list = Collections.singletonList(cvElastic);
        return new PageImpl<>(list, pageable, 0);
    }
}
