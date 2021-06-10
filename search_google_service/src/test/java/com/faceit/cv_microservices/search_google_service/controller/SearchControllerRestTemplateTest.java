package com.faceit.cv_microservices.search_google_service.controller;

import com.faceit.cv_microservices.search_google_service.dto.request.CvRequest;
import com.faceit.cv_microservices.search_google_service.dto.request.PreviousWorkRequest;
import com.faceit.cv_microservices.search_google_service.dto.request.SalaryRequest;
import com.faceit.cv_microservices.search_google_service.dto.request.UserRequest;
import com.faceit.cv_microservices.search_google_service.model.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SearchControllerRestTemplateTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void search() {
        final CvRequest cv = new CvRequest(
                "1",
                "test",
                new SalaryRequest("test", 1),
                new UserRequest("from", "", "no"),
                Collections.singletonList("test"),
                "test",
                Collections.singletonList(new PreviousWorkRequest("test", "test", "1990")),
                "test");

        final ResponseEntity<List<Reference>> responseEntity = this.restTemplate.exchange(
                "/search",
                HttpMethod.POST,
                new HttpEntity<>(cv, new HttpHeaders()),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
