package com.faceit.cv_microservices.auth_service.service;

import com.faceit.cv_microservices.auth_service.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {

    User save(@RequestBody User user);

    User findUserByUserName(@PathVariable String userName);
}
