package com.faceit.cv_microservices.auth_service.security.services;

import com.faceit.cv_microservices.auth_service.model.User;
import com.faceit.cv_microservices.auth_service.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Log4j2
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = this.userService.findUserByUserName(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return new UserDetailsImpl(user);
    }
}
