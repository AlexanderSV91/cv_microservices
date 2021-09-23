package com.faceit.cv_microservices.gateway_server.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private static final String PREFIX_CLAIM_ROLE = "roles";

    @Value("${app.auth.tokenSecret}")
    private String jwtSecret;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        try {
            final Jws<Claims> claims = Jwts
                    .parser()
                    .setSigningKey(this.jwtSecret)
                    .parseClaimsJws(authentication.getCredentials().toString());

            if (Objects.isNull(claims)
                    || claims.getBody().getExpiration().before(new Date(System.currentTimeMillis()))) {
                throw new Exception();
            }

            final List<String> split = Arrays.asList(claims.getBody().get(PREFIX_CLAIM_ROLE).toString().split(","));
            return Mono.just(new UsernamePasswordAuthenticationToken(
                    claims.getBody().getSubject(),
                    null,
                    split.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())));
        } catch (Exception e) {
            return Mono.empty();
        }
    }
}
