package com.faceit.cv_microservices.auth_service.controllers;

import com.faceit.cv_microservices.auth_service.dto.request.SignInRequest;
import com.faceit.cv_microservices.auth_service.dto.request.SignUpRequest;
import com.faceit.cv_microservices.auth_service.dto.response.JwtResponse;
import com.faceit.cv_microservices.auth_service.mapper.UserMapper;
import com.faceit.cv_microservices.auth_service.security.jwt.JwtUtils;
import com.faceit.cv_microservices.auth_service.security.services.UserDetailsImpl;
import com.faceit.cv_microservices.auth_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "registration and authentication users")
@Log4j2
public class AuthControllerRest {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;

    public AuthControllerRest(AuthenticationManager authenticationManager,
                              UserService userService,
                              PasswordEncoder encoder,
                              UserMapper userMapper,
                              JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.encoder = encoder;
        this.userMapper = userMapper;
        this.jwtUtils = jwtUtils;
    }

    @Operation(summary = "authenticate user", tags = "Auth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get jwtResponse", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = JwtResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema(hidden = true))})
    })
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody SignInRequest signInRequest) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUserName(), signInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwt = this.jwtUtils.generateJwtToken(authentication);
        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, this.userMapper.toUserResponse(userDetails.getUser())));
    }

    @Operation(summary = "register user", tags = "Auth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get string", content = {@Content(schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema(hidden = true))})
    })
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpRequest signUpRequest) {
        signUpRequest.setPassword(this.encoder.encode(signUpRequest.getPassword()));
        this.userService.save(this.userMapper.toUser(signUpRequest));
        return ResponseEntity.ok("User registered successfully!");
    }
}
