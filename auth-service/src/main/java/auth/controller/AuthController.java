package auth.controller;

import auth.dto.LoginRequest;
import auth.dto.RegisterRequest;
import auth.entity.PersonalDTO;
import auth.exceptions.UserAuthException;
import auth.feign.UserClient;
import auth.repository.UserRepository;
import auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class AuthController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthService authService;
    private UserClient authClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/auth/some")
    public LoginRequest some() {
        return LoginRequest.builder().email("alxxxkh@gmail.com").password("123").build();
    }

    @PutMapping("/auth/update")
    public LoginRequest some(@RequestBody @Valid LoginRequest request) {

        auth.entity.User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.saveAndFlush(user);
        return null;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) throws UserAuthException {

        return ResponseEntity.ok().body(authService.login(request));

//
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.getEmail(), request.getPassword())
//            );
//
//            User user = (User) authentication.getPrincipal();
//            System.out.println(user);
//            String accessToken = jwtUtil.generateAccessToken(user);
//            AuthResponse response = new AuthResponse(user.getUsername(), accessToken);
//            return ResponseEntity.ok().body(response);
//        } catch (BadCredentialsException ex) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("auth/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) {

        return null;
    }

    @GetMapping("/auth/m")
    public void getAll() {
        List<PersonalDTO> personalDTOS = authClient.getAll();
    }
}
