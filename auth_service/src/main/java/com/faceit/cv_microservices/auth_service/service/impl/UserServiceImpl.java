package com.faceit.cv_microservices.auth_service.service.impl;

import com.faceit.cv_microservices.auth_service.model.User;
import com.faceit.cv_microservices.auth_service.service.UserService;
import com.faceit.cv_microservices.auth_service.service.UserServiceFeignClient;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserServiceFeignClient userServiceFeignClient;

    public UserServiceImpl(UserServiceFeignClient userServiceFeignClient) {
        this.userServiceFeignClient = userServiceFeignClient;
    }

    @Override
    public User save(User user) {
        return this.userServiceFeignClient.save(user);
    }

    @Override
    public User findUserByUserName(String userName) {
        return this.userServiceFeignClient.findUserByUserName(userName);
    }
}
