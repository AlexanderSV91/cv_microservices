package com.faceit.cv_microservices.auth_service.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestControllerAdvice
public class AdviceControllerRest {

    @ExceptionHandler(BadCredentialsException.class)
    public Map<String, String> badCredentialEx(Exception e) {
        log.error(BadCredentialsException.class.getSimpleName());
        Map<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return map;
    }
}
