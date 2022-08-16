package com.user;

import com.user.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserApplication {
    private UserRepository userRepository;

    public static void main(String[] args) {

        SpringApplication.run(UserApplication.class, args);
    }
}
