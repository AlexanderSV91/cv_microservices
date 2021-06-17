package com.faceit.cv_microservices.auth_service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Schema(description = "User response")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {

    @Schema(description = "Identifier")
    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private int age;
    private Set<String> roles;
}
