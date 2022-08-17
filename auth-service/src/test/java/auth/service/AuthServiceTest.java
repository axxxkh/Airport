package auth.service;

import auth.dto.AuthResponse;
import auth.dto.LoginRequest;
import auth.dto.RegisterRequest;
import auth.exceptions.UserAuthException;
import auth.exceptions.UserRegisterException;
import auth.feign.UserClient;
import auth.jwt.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class AuthServiceTest {

    @Autowired
    UserClient userClient;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    public AuthService authService;

    private String errorMessage;

    @Test
    public void loginTest_ExpectUserAuthExceptionWrongEmail() throws UserAuthException {
        LoginRequest requestWrongEmail = LoginRequest.builder()
                .email("test@gmail.com")
                .password("test")
                .build();

        errorMessage = String.format("User %s not found", requestWrongEmail.getEmail());
        UserAuthException exception = Assertions.assertThrows(UserAuthException.class, () -> authService.login(requestWrongEmail));
        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void loginTest_ExpectUserAuthExceptionWrongPassword() throws UserAuthException {
        LoginRequest requestWrongPassword = LoginRequest.builder()
                .email("alxxxkh@gmail.com")
                .password("test")
                .build();

        errorMessage = String.format("Wrong password for user with email %s", requestWrongPassword.getEmail());

        UserAuthException exception = Assertions.assertThrows(UserAuthException.class, () -> authService.login(requestWrongPassword));
        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void loginTest_ExpectSuccess() throws UserAuthException {
        LoginRequest request = LoginRequest.builder()
                .email("alxxxkh@gmail.com")
                .password("123")
                .build();

        AuthResponse response = authService.login(request);
        Assertions.assertEquals(request.getEmail(), response.getEmail());
    }

    @Test
    public void registerUserTest_ExpectUserRegisterExceptionUserExist() {
        RegisterRequest request = RegisterRequest.builder()
                .email("alxxxkh@gmail.com")
                .password("123")
                .secretQuestion("TestQuestion")
                .secretAnswer("TestAnswer")
                .build();

        errorMessage = String.format("User with email %s already exist", request.getEmail());

        UserRegisterException exception = Assertions.assertThrows(UserRegisterException.class, () -> authService.registerUser(request));
        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void registerUserTest_ExpectValidationException() {
        RegisterRequest request = RegisterRequest.builder()
                .email("axkh@gmail.com")
                .password("123")
                .secretAnswer("TestAnswer")
                .build();

        errorMessage = "Secret question can't be blank";

        ConstraintViolationException exception = Assertions.assertThrows(ConstraintViolationException.class, () -> authService.registerUser(request));
        Assertions.assertTrue(exception.getMessage().contains(errorMessage));
    }

    @Test
    public void registerUserTest_ExpectSuccess() throws UserRegisterException {
        RegisterRequest request = RegisterRequest.builder()
                .email("axkhkk@gmail.com")
                .password("123")
                .secretQuestion("TestQuestion")
                .secretAnswer("TestAnswer")
                .build();

        errorMessage = "Secret question can't be blank";

        AuthResponse response = authService.registerUser(request);
        Assertions.assertTrue(response.getEmail().equals(request.getEmail())
                && !response.getAccessToken().isBlank());
    }
}
