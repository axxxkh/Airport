package auth.feign;

import auth.dto.AuthResponse;
import auth.entity.PersonalDTO;
import auth.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(value="user-service", url = "localhost:8087/user")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET,value = "/")
    public List<PersonalDTO> getAll();

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public Optional<User> getUser ();

    @RequestMapping(method = RequestMethod.GET,value = "/em/")
    public ResponseEntity<User> getByEmail(@RequestParam(value = "email") String email);

}
