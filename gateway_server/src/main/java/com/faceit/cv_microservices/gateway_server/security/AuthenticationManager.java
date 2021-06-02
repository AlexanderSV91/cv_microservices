package com.faceit.cv_microservices.gateway_server.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private static final String PREFIX_CLAIM_ROLE = "roles";

    private final JwtUtil jwtUtil;

    public AuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();

        try {
            Jws<Claims> claims = this.jwtUtil.getAllClaimsFromToken(authToken);

            if (Objects.isNull(claims)) {
                return Mono.empty();
            }

            Date expires = claims.getBody().getExpiration();
            Date currentDate = new Date(System.currentTimeMillis());
            if (expires.before(currentDate)) {
                return Mono.empty();
            }

            List<String> split = Arrays.asList(claims.getBody().get(PREFIX_CLAIM_ROLE).toString().split(","));
            Collection<? extends GrantedAuthority> authorities = split.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(claims.getBody().getSubject(), null, authorities);
            return Mono.just(usernamePasswordAuthenticationToken);
        } catch (Exception e) {
            return Mono.empty();
        }
    }
}
