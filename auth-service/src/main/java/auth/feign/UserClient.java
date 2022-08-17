package auth.feign;

import auth.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "user-service")
/* Feign client to get data from user service (service that contains user information login, password etc */
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    Optional<User> getByEmail(@RequestParam(value = "email") String email);

    @RequestMapping(method = RequestMethod.POST, value = "/register/")
    User registerUser(@RequestBody User user);

}
