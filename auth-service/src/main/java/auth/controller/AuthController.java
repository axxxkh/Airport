package auth.controller;

import auth.dto.AuthResponse;
import auth.dto.LoginRequest;
import auth.dto.RegisterRequest;
import auth.exceptions.UserAuthException;
import auth.exceptions.UserRegisterException;
import auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Data
@AllArgsConstructor

/* This controller contains open endpoints to login or register user
 *  returns login (email) and issued JWT*/
public class AuthController {
    private AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) throws UserAuthException {
        return ResponseEntity.ok().body(authService.login(request));
    }

    @PostMapping("auth/register")
    public AuthResponse register(@RequestBody @Valid RegisterRequest request) throws UserRegisterException {
        return authService.registerUser(request);
    }
}
