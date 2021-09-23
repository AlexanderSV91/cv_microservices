package com.faceit.cv_microservices.auth_service.mapper;

import com.faceit.cv_microservices.auth_service.dto.request.SignUpRequest;
import com.faceit.cv_microservices.auth_service.dto.response.UserResponse;
import com.faceit.cv_microservices.auth_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "id", ignore = true)})
    User toUser(SignUpRequest signUpRequest);

    @Mapping(expression = "java(user.getRoles().stream().map(role -> role.getName().substring(5)).collect(java.util.stream.Collectors.toSet()))", target = "roles")
    UserResponse toUserResponse(User user);
}
