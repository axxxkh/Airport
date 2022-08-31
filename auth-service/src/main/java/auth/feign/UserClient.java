package auth.feign;

import auth.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Email;
import java.util.Optional;

@Validated
@FeignClient("user-service")
/* Feign client to get data from user service (service that contains user information login, password etc */
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/user/")
    Optional<User> getByEmail(@RequestParam(value = "email") @Email String email);

    @RequestMapping(method = RequestMethod.POST, value = "/user/register/")
    User registerUser(@RequestBody User user);
}
