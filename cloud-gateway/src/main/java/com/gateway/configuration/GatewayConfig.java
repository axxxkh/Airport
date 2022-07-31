package com.gateway.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Data

/* This class responsible for gateway configuration and implements filters to predefined routes*/
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter authenticationFilter;
//        @Autowired
//    private AdminAuthenticationFilter adminAuthenticationFilter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/auth/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://auth-service"))

                .route(r -> r.path("/user/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://user-service"))

                .route(r -> r.path("/flight/**", "/passenger/**", "/ticket/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://flight-service"))

                .build();
    }
}
