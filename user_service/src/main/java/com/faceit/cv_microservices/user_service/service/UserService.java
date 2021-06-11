package com.faceit.cv_microservices.user_service.service;

import com.faceit.cv_microservices.user_service.model.User;

public interface UserService {

    User save(User user);

    User findUserByUserName(String userName);

    boolean existsUserByUserName(String userName);

    void deleteById(long id);
}
