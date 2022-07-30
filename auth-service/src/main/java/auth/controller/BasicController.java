package auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth/")
public class BasicController {

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
