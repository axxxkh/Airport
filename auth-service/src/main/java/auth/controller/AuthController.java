package auth.controller;

import auth.dto.AuthResponse;
import auth.dto.LoginRequest;
import auth.dto.RegisterRequest;
import auth.exceptions.UserAuthException;
import auth.exceptions.UserRegisterException;
import auth.feign.UserClient;
import auth.service.impl.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AuthController {
    private AuthServiceImpl authService;
    private UserClient userClient;

    @GetMapping("/auth/some")
    public LoginRequest some() {
        return LoginRequest.builder().email("alxxxkh@gmail.com").password("123").build();
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
    public AuthResponse register(@RequestBody @Valid RegisterRequest request) throws UserRegisterException {
        return authService.registerUser(request);
    }
}
