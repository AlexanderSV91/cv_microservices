package com.faceit.cv_microservices.cv_service.service.impl;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import com.faceit.cv_microservices.cv_service.mapper.CvMongoMapper;
import com.faceit.cv_microservices.cv_service.model.mongo.CvMongo;
import com.faceit.cv_microservices.cv_service.model.mongo.PreviousWorkMongo;
import com.faceit.cv_microservices.cv_service.model.mongo.SalaryMongo;
import com.faceit.cv_microservices.cv_service.model.mongo.UserMongo;
import com.faceit.cv_microservices.cv_service.service.CvStorageMongoService;
import com.faceit.cv_microservices.cv_service.service.CvStorageServiceFeignClient;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
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
public class CvStorageMongoServiceImpl implements CvStorageMongoService {

    private final CvStorageServiceFeignClient cvStorageServiceFeignClient;
    private final CvMongoMapper cvMongoMapper;

    public CvStorageMongoServiceImpl(CvStorageServiceFeignClient cvStorageServiceFeignClient,
                                     CvMongoMapper cvMongoMapper) {
        this.cvStorageServiceFeignClient = cvStorageServiceFeignClient;
        this.cvMongoMapper = cvMongoMapper;
    }

    @Retry(name = "cv-mongo-resilience", fallbackMethod = "findAllCvFallback")
    @CircuitBreaker(name = "cv-mongo-resilience")
    @Bulkhead(name = "cv-mongo-resilience")
    @Override
    public Page<CvResponse> findAllCv(Pageable pageable) {
        return pageEntityToPageResponse(this.cvStorageServiceFeignClient.findAllCvMongo(pageable));
    }

    @Override
    public CvMongo findById(String id) {
        return this.cvStorageServiceFeignClient.findByIdMongo(id);
    }

    private PageImpl<CvResponse> pageEntityToPageResponse(Page<CvMongo> page) {
        return new PageImpl<>(this.cvMongoMapper.toCvResponseList(page.getContent()), page.getPageable(), page.getTotalElements());
    }

    private Page<CvMongo> findAllCvFallback(final Pageable pageable, final Exception ex) {
        CvMongo cv = new CvMongo(
                "-1",
                "no",
                new SalaryMongo("no", -1),
                new UserMongo("no", "no", "no"),
                Collections.singletonList("no"),
                "no",
                Collections.singletonList(new PreviousWorkMongo("no", "no", "-1")),
                "no");
        List<CvMongo> list = Collections.singletonList(cv);
        return new PageImpl<>(list, pageable, 0);
    }
}
