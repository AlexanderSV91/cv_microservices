package com.faceit.cv_microservices.user_service.service;

import com.faceit.cv_microservices.user_service.model.Role;

public interface RoleService {

    Role findRoleByName(String name);
}
