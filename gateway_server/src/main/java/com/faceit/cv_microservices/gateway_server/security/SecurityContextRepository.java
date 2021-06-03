package com.faceit.cv_microservices.gateway_server.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Log4j2
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private static final String PREFIX_BEARER = "Bearer ";

    private final AuthenticationManager authenticationManager;

    public SecurityContextRepository(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange swe) {
        final String authHeader = swe.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (Objects.nonNull(authHeader) && authHeader.startsWith(PREFIX_BEARER)) {
            final String authToken = authHeader.substring(7);
            return this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authToken, authToken))
                    .map(SecurityContextImpl::new);
        } else {
            return Mono.empty();
        }
    }
}
