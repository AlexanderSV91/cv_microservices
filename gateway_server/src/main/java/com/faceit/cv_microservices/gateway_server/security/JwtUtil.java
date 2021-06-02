package com.faceit.cv_microservices.gateway_server.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class JwtUtil {

    @Value("${app.auth.tokenSecret}")
    private String jwtSecret;

    public Jws<Claims> getAllClaimsFromToken(String authToken) {
        return Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(authToken);
    }
}
