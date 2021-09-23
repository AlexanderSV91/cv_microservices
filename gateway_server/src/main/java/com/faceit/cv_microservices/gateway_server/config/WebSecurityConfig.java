package com.faceit.cv_microservices.gateway_server.config;

import com.faceit.cv_microservices.gateway_server.security.AuthenticationManager;
import com.faceit.cv_microservices.gateway_server.security.SecurityContextRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
public class WebSecurityConfig {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    public WebSecurityConfig(AuthenticationManager authenticationManager,
                             SecurityContextRepository securityContextRepository) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.cors()
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable()
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authenticationManager(this.authenticationManager)
                .securityContextRepository(this.securityContextRepository)
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)))
                .accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN)))
                .and()
                .authorizeExchange()
                .pathMatchers("/auth-service/api/v1/auth/signin", "/auth-service/api/v1/auth/signup").permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .build();
    }
}
