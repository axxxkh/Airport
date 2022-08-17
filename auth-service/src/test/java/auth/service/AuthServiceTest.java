package auth.service;

import auth.dto.LoginRequest;
import auth.exceptions.UserAuthException;
import auth.feign.UserClient;
import auth.jwt.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthServiceTest {

    @Autowired
    UserClient userClient;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    public AuthService authService;

    @Test
    public void loginTest_ExpectUserAuthExceptionWrongEmail() throws UserAuthException {
        LoginRequest requestWrongEmail = LoginRequest.builder()
                .email("test@gmail.com")
                .password("test")
                .build();

        UserAuthException exception = Assertions.assertThrows(UserAuthException.class, () -> authService.login(requestWrongEmail));
        Assertions.assertEquals(String.format("User %s not found", requestWrongEmail.getEmail()), exception.getMessage());
    }

    @Test
    public void loginTest_ExpectUserAuthExceptionWrongPassworf() throws UserAuthException {
        LoginRequest requestWrongPassword = LoginRequest.builder()
                .email("alxxxkh@gmail.com")
                .password("test")
                .build();

        UserAuthException exception = Assertions.assertThrows(UserAuthException.class, () -> authService.login(requestWrongPassword));
        Assertions.assertEquals(String.format("Wrong password for user with email %s", requestWrongPassword.getEmail()), exception.getMessage());
    }
}
