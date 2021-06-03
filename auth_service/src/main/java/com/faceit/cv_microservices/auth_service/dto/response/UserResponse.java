package com.faceit.cv_microservices.auth_service.dto.response;

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
    private String firstName;
    private String lastName;
    private int age;
    private Set<String> roles;
}
