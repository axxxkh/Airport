package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudGateway {
//@Bean
//public RouterValidator routerValidator(){
//    return new RouterValidator();
//}

    public static void main(String[] args) {
        SpringApplication.run(CloudGateway.class, args);
    }

}
