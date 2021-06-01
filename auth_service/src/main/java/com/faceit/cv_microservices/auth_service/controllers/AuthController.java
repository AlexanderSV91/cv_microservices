package com.faceit.cv_microservices.auth_service.controllers;

import com.faceit.cv_microservices.auth_service.dto.request.SignInRequest;
import com.faceit.cv_microservices.auth_service.dto.request.SignUpRequest;
import com.faceit.cv_microservices.auth_service.dto.response.JwtResponse;
import com.faceit.cv_microservices.auth_service.mapper.UserMapper;
import com.faceit.cv_microservices.auth_service.security.jwt.JwtUtils;
import com.faceit.cv_microservices.auth_service.security.services.UserDetailsImpl;
import com.faceit.cv_microservices.auth_service.service.UserService;
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
@Log4j2
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager,
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

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody SignInRequest signInRequest) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUserName(), signInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, this.userMapper.toUserResponse(userDetails.getUser())));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpRequest signUpRequest) {
        signUpRequest.setPassword(this.encoder.encode(signUpRequest.getPassword()));
        this.userService.save(this.userMapper.toUser(signUpRequest));
        return ResponseEntity.ok("User registered successfully!");
    }
}
