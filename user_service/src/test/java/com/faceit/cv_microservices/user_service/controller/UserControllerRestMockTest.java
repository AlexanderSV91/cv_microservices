package com.faceit.cv_microservices.user_service.controller;

import com.faceit.cv_microservices.user_service.dto.request.UserRequest;
import com.faceit.cv_microservices.user_service.model.User;
import com.faceit.cv_microservices.user_service.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerRestMockTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void save() throws Exception {
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
    @Transactional
    void findUserByUserName() throws Exception {
        final User user = User.builder().userName("test").password("test").firstName("f").lastName("l").age(9).build();
        this.userRepository.save(user);

        this.mockMvc.perform(get("/user/{userName}", user.getUserName())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(user.getUserName()))
                .andExpect(jsonPath("$.password").value(user.getPassword()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.age").value(user.getAge()))
                .andDo(MockMvcResultHandlers.print());
    }
}
