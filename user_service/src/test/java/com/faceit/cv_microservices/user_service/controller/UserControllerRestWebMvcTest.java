package com.faceit.cv_microservices.user_service.controller;

import com.faceit.cv_microservices.user_service.dto.request.UserRequest;
import com.faceit.cv_microservices.user_service.model.Role;
import com.faceit.cv_microservices.user_service.model.User;
import com.faceit.cv_microservices.user_service.model.enumeration.RoleType;
import com.faceit.cv_microservices.user_service.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerRestWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private UserService userService;

    @Test
    @Transactional
    void save() throws Exception {
        final Role role = new Role(1, RoleType.ROLE_USER.getNameRoleType());
        final User user = new User(1, "test", "test", "f", "l", 9, Collections.singleton(role));
        final User build = User.builder().userName("test").password("test").firstName("f").lastName("l").age(9).build();

        when(this.userService.save(build)).thenReturn(user);

        final UserRequest userRequest = new UserRequest("test", "test", "f", "l", 9);
        final MvcResult mvcResult = this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(userRequest.getUserName()))
                .andExpect(jsonPath("$.password").value(userRequest.getPassword()))
                .andExpect(jsonPath("$.firstName").value(userRequest.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(userRequest.getLastName()))
                .andExpect(jsonPath("$.age").value(userRequest.getAge()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        final User readValue = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);
        this.mockMvc.perform(delete("/user/{id}", readValue.getId()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void findUserByUserName() throws Exception {
        final Role role = new Role(1, RoleType.ROLE_USER.getNameRoleType());
        final User user = new User(1, "1", "1", "1", "1", 1, Collections.singleton(role));

        when(this.userService.findUserByUserName("1")).thenReturn(user);

        mockMvc.perform(get("/user/{userName}", user.getUserName())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(user.getUserName()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.age").value(user.getAge()))
                .andDo(MockMvcResultHandlers.print());
    }
}
