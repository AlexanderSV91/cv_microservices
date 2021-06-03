package com.faceit.cv_microservices.auth_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private Set<Role> roles;
}
