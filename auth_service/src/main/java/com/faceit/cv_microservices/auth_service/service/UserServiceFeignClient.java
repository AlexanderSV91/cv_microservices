package com.faceit.cv_microservices.auth_service.service;

import com.faceit.cv_microservices.auth_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-service")
public interface UserServiceFeignClient {

    @PostMapping("/user")
    User save(@RequestBody User user);

    @GetMapping("/user/{userName}")
    User findUserByUserName(@PathVariable String userName);
}
