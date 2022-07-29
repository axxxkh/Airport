package com.gateway.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
//@RefreshScope
@Component
@AllArgsConstructor
//@NoArgsConstructor
//@Data
public class AdminAuthenticationFilter implements GatewayFilter {
    private String role;

    public AdminAuthenticationFilter authenticationFilter(String role){
        return new AdminAuthenticationFilter(role);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        List<String> roles = new ArrayList<>(request.getHeaders().getOrEmpty("role"));
        if (!roles.contains("ADMIN")) {
            return this.onError(exchange, "You aren`t authorized for this route", HttpStatus.FORBIDDEN);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}
