package com.faceit.cv_microservices.gateway_server.interceptor;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Log4j2
@Component
public class WebRequestInterceptor implements WebFilter {

    @NotNull
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange,
                             WebFilterChain webFilterChain) {
        log.info(" Path=" + serverWebExchange.getRequest().getPath());
        return webFilterChain.filter(serverWebExchange);
    }
}
