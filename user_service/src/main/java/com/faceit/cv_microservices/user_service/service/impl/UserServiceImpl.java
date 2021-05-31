package com.faceit.cv_microservices.user_service.service.impl;

import com.faceit.cv_microservices.user_service.model.User;
import com.faceit.cv_microservices.user_service.repository.UserRepository;
import com.faceit.cv_microservices.user_service.service.RoleService;
import com.faceit.cv_microservices.user_service.service.UserService;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User save(User user) {
        user.setRoles(Collections.singleton(this.roleService.findRoleByName("ROLE_USER")));
        return this.userRepository.save(user);
    }

    @Override
    public User findUserByUserName(String userName) {
        return this.userRepository.findUserByUserName(userName).orElseThrow(FindException::new);
    }

    @Override
    public boolean existsUserByUserName(String userName) {
        return this.userRepository.existsUserByUserName(userName);
    }
}
