package user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import user.repository.UserRepository;

@SpringBootApplication
@EnableEurekaClient
public class UserApplication {
    private UserRepository userRepository;

    public static void main(String[] args) {

        SpringApplication.run(UserApplication.class, args);
    }
}
