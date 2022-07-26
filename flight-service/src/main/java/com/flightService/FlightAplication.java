package com.flightService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FlightAplication {
    public static void main(String[] args) {
        SpringApplication.run(FlightAplication.class,args);
    }
}
