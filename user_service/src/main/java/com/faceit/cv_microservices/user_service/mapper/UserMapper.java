package com.faceit.cv_microservices.user_service.mapper;

import com.faceit.cv_microservices.user_service.dto.request.UserRequest;
import com.faceit.cv_microservices.user_service.dto.response.UserResponse;
import com.faceit.cv_microservices.user_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "id", ignore = true)})
    User toUser(UserRequest userRequest);

    UserResponse toUserResponse(User user);
}
