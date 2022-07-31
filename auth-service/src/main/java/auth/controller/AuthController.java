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

/* This controller contains open endpoints to login or register user
*  returns login (email) and issued JWT*/
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

    }

    @PostMapping("auth/register")
    public AuthResponse register(@RequestBody @Valid RegisterRequest request) throws UserRegisterException {
        return authService.registerUser(request);
    }
}
