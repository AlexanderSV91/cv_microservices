package com.faceit.cv_microservices.user_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
}
