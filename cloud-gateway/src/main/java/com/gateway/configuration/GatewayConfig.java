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
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/auth/**")
                        .filters(f -> f.filter(authenticationFilter))
//                        .uri("http://localhost:8084/"))
                        .uri("lb://auth-service"))

                .route(r -> r.path("/user/**")
                        .filters(f -> f.filter(authenticationFilter))
//                        .uri("http://localhost:8087/"))
                        .uri("lb://user-service"))

                .route(r -> r.path("/flight/**")
                        .filters(f -> f.filter(authenticationFilter))
//                        .uri("http://localhost:8081/"))
                        .uri("lb://flight-service"))

                .route(r -> r.path("/passenger/**")
                        .filters(f -> f.filter(authenticationFilter))
//                        .uri("http://localhost:8081/"))
                        .uri("lb://flight-service"))
                .build();
    }
}
