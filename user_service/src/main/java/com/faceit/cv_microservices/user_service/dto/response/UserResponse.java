package com.faceit.cv_microservices.user_service.dto.response;

import com.faceit.cv_microservices.user_service.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {

    private long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private Set<Role> roles;
}
