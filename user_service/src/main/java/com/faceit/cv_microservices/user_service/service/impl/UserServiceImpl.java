package com.faceit.cv_microservices.user_service.service.impl;

import com.faceit.cv_microservices.user_service.model.Role;
import com.faceit.cv_microservices.user_service.model.User;
import com.faceit.cv_microservices.user_service.model.enumeration.RoleType;
import com.faceit.cv_microservices.user_service.repository.UserRepository;
import com.faceit.cv_microservices.user_service.service.RoleService;
import com.faceit.cv_microservices.user_service.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public User save(User user) {
        final Role role = this.roleService.findRoleByName(RoleType.ROLE_USER.getNameRoleType());
        user.setRoles(Collections.singleton(role));
        return this.userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByUserName(String userName) {
        return this.userRepository.findUserByUserName(userName).orElseThrow(FindException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsUserByUserName(String userName) {
        return this.userRepository.existsUserByUserName(userName);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        this.userRepository.deleteById(id);
    }
}
