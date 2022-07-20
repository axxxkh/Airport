package auth.controller;

import auth.dto.LoginRequest;
import auth.entity.User;
import auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController()
@RequestMapping("/auth/")
public class BasicController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("user")
    public String helloUser() {
        return "Hello User";
    }

//    @GetMapping("auth")
//    public LoginRequest auth () {
//        return LoginRequest.builder().email("alxxxkh@gmail.com").password("123").build();
//    }

    @GetMapping("admin")
    public String helloYou() {
        return "fdfdfdf";
    }

//    @PostMapping("admin")
//    public ResponseEntity<User> helloAdmin(@RequestBody User user) {
//        User passenger = userRepository.findByUsername("alxxxkh@gmail.com").orElseThrow(() -> new UsernameNotFoundException("dd"));
//        URI uri = URI.create("/hello/" + passenger.getId());
//        return ResponseEntity.created(uri).body(passenger);
//    }

}
