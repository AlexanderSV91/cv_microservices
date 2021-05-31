package com.faceit.cv_microservices.user_service.controller;

import com.faceit.cv_microservices.user_service.dto.request.UserRequest;
import com.faceit.cv_microservices.user_service.dto.response.UserResponse;
import com.faceit.cv_microservices.user_service.mapper.UserMapper;
import com.faceit.cv_microservices.user_service.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserControllerRest {

    private final UserMapper userMapper;
    private final UserService userService;

    public UserControllerRest(UserMapper userMapper,
                              UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping
    public UserResponse save(@RequestBody UserRequest userRequest) {
        if (this.userService.existsUserByUserName(userRequest.getUserName())) {
            throw new RuntimeException();
        }
        return this.userMapper.toUserResponse(this.userService.save(this.userMapper.toUser(userRequest)));
    }

    @GetMapping("/{userName}")
    public UserResponse findUserByUserName(@PathVariable String userName) {
        return this.userMapper.toUserResponse(this.userService.findUserByUserName(userName));
    }
}
