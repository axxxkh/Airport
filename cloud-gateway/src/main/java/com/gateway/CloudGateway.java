package com.gateway;

import com.gateway.configuration.AuthenticationFilter;
import com.gateway.configuration.JwtUtil;
import com.gateway.configuration.RouterValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
public class CloudGateway {


    public static void main(String[] args) {
        SpringApplication.run(CloudGateway.class, args);
    }

}