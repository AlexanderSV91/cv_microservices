package com.faceit.cv_microservices.user_service.service.impl;

import com.faceit.cv_microservices.user_service.model.Role;
import com.faceit.cv_microservices.user_service.repository.RoleRepository;
import com.faceit.cv_microservices.user_service.service.RoleService;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleByName(String name) {
        return this.roleRepository.findRoleByName(name).orElseThrow(FindException::new);
    }
}
