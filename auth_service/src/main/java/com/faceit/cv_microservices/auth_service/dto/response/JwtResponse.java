package com.faceit.cv_microservices.auth_service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Jwt response")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {

    @Schema(description = "token")
    private String token;
    @Schema(description = "user")
    private UserResponse user;
}
