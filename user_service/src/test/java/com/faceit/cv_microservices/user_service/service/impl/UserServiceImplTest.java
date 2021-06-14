package com.faceit.cv_microservices.user_service.service.impl;

import com.faceit.cv_microservices.user_service.model.Role;
import com.faceit.cv_microservices.user_service.model.User;
import com.faceit.cv_microservices.user_service.model.enumeration.RoleType;
import com.faceit.cv_microservices.user_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @Transactional
    void save() {
        final User userBuild = User.builder().userName("test").password("test").firstName("f").lastName("l").age(9).build();

        when(this.userRepository.save(userBuild)).thenReturn(userBuild);

        final User user = this.userService.save(userBuild);
        this.userService.deleteById(user.getId());
        assertEquals(userBuild.getUserName(), user.getUserName());
        assertEquals(userBuild.getFirstName(), user.getFirstName());
        assertEquals(userBuild.getLastName(), user.getLastName());
        assertEquals(userBuild.getAge(), user.getAge());
    }

    @Test
    void findUserByUserName() {
        final Role role = new Role(1, RoleType.ROLE_USER.getNameRoleType());
        final User user = new User(1, "1", "1", "1", "1", 1, Collections.singleton(role));

        when(this.userRepository.findUserByUserName(user.getUserName())).thenReturn(Optional.of(user));

        final User userFind = this.userService.findUserByUserName(user.getUserName());
        assertEquals(user.getId(), userFind.getId());
        assertEquals(user.getUserName(), userFind.getUserName());
        assertEquals(user.getFirstName(), userFind.getFirstName());
        assertEquals(user.getLastName(), userFind.getLastName());
        assertEquals(user.getAge(), userFind.getAge());
    }

    @Test
    void existsUserByUserName() {
        final String userName = "1";

        when(this.userRepository.existsUserByUserName(userName)).thenReturn(true);

        final boolean isExist = this.userService.existsUserByUserName(userName);

        assertTrue(isExist);
    }

    @Test
    @Transactional
    void deleteById() {
        final User userBuild = User.builder().userName("test").password("test").firstName("f").lastName("l").age(9).build();

        when(this.userRepository.save(userBuild)).thenReturn(userBuild);

        this.userService.deleteById(this.userService.save(userBuild).getId());

        final boolean isExist = this.userService.existsUserByUserName(userBuild.getUserName());

        assertFalse(isExist);
    }
}
