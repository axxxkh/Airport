package com.flightService;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class FlightApplication {
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(FlightApplication.class,args);
    }
}
