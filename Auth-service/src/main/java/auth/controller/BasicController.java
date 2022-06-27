package auth.controller;

import auth.Repository.PassengerRepository;
import auth.auth.AuthRequest;
import auth.dto.PassengerDTO;
import auth.entity.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("hello")
public class BasicController {

    @Autowired
    private PassengerRepository passengerRepository;

    @GetMapping("user")
    public String helloUser() {
        return "Hello User";
    }

    @GetMapping("auth")
    public AuthRequest auth () {
        return AuthRequest.builder().email("alxxxkh@gmail.com").password("123").build();
    }

    @GetMapping("admin")
    public String helloYou() {
        return "fdfdfdf";
    }
    @PostMapping("admin")
    public ResponseEntity<Passenger> helloAdmin(@RequestBody PassengerDTO passengerDTO) {
        Passenger passenger = passengerRepository.findByUsername("alxxxkh@gmail.com").orElseThrow(() -> new UsernameNotFoundException("dd"));
        URI uri = URI.create("/hello/" + passenger.getId());
        return ResponseEntity.created(uri).body(passenger);
    }

}
