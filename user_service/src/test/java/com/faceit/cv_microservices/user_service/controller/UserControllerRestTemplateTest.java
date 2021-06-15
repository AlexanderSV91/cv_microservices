package com.faceit.cv_microservices.user_service.controller;

import com.faceit.cv_microservices.user_service.dto.request.UserRequest;
import com.faceit.cv_microservices.user_service.dto.response.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerRestTemplateTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Transactional
    void save() {
        final UserRequest userRequest = new UserRequest("test", "test", "f", "l", 5);
        final ResponseEntity<UserResponse> responseEntity = this.restTemplate.exchange(
                "/user",
                HttpMethod.POST,
                new HttpEntity<>(userRequest, new HttpHeaders()),
                new ParameterizedTypeReference<>() {});

        final UserResponse userResponse = Objects.requireNonNull(responseEntity.getBody());
        this.restTemplate.delete("/user/" + userResponse.getId());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userRequest.getUserName(), userResponse.getUserName());
    }

    @Test
    void findUserByUserName() {
        final String userName = "1";
        final UserResponse userResponse = this.restTemplate.getForObject("/user/" + userName, UserResponse.class);
        assertEquals(userName, userResponse.getUserName());
    }
}
