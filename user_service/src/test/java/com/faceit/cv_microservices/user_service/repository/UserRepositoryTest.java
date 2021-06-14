package com.faceit.cv_microservices.user_service.repository;

import com.faceit.cv_microservices.user_service.model.Role;
import com.faceit.cv_microservices.user_service.model.User;
import com.faceit.cv_microservices.user_service.model.enumeration.RoleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void save() {
        final Role role = new Role(1, RoleType.ROLE_USER.getNameRoleType());
        final User userSave = new User(1, "1", "1", "1", "1", 1, Collections.singleton(role));

        final User user = this.userRepository.save(userSave);
        this.userRepository.deleteById(user.getId());

        assertEquals(userSave.getUserName(), user.getUserName());
        assertEquals(userSave.getFirstName(), user.getFirstName());
        assertEquals(userSave.getLastName(), user.getLastName());
        assertEquals(userSave.getAge(), user.getAge());
    }

    @Test
    void findUserByUserName() {
        final String userName = "1";

        final Optional<User> user = this.userRepository.findUserByUserName(userName);

        assertTrue(user.isPresent());
        assertEquals(userName, user.get().getUserName());
    }

    @Test
    void existsUserByUserName() {
        final String userName = "1";

        final boolean isExist = this.userRepository.existsUserByUserName(userName);

        assertTrue(isExist);
    }
}
