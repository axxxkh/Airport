package auth.controller;

import auth.dto.AuthResponse;
import auth.dto.LoginRequest;
import auth.dto.RegisterRequest;
import auth.exceptions.UserAuthException;
import auth.exceptions.UserRegisterException;
import auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Data
@AllArgsConstructor
@Validated

/* This controller contains open endpoints to login or register user
 *  returns login (email) and issued JWT*/
public class AuthController {
    private AuthService authService;

    @PostMapping(path = "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) throws UserAuthException {
        return ResponseEntity.ok().body(authService.login(request));
    }

    @PostMapping(value = "auth/register", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) throws UserRegisterException {
        return ResponseEntity.ok().body(authService.registerUser(request));
    }
}
