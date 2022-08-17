package auth.service;

import auth.dto.LoginRequest;
import auth.exceptions.UserAuthException;
import auth.feign.UserClient;
import auth.jwt.JwtUtil;
import auth.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthServiceTest {
//
//    @Autowired
//    UserClient userClient;
//
////    @Autowired
//    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
////    @Autowired
//    JwtUtil jwtUtil = new JwtUtil();
//
////    @Autowired
//    public AuthService authService = new AuthServiceImpl(userClient, passwordEncoder, jwtUtil);
//
//    @Test
//    public void loginTest_ExpectUserAuthException() throws UserAuthException {
//        LoginRequest request = LoginRequest.builder()
////                .email("test@test.com")
//                .email("alxxxkh@gmail.com")
//                .password("test")
//                .build();
//
//        System.out.println(authService.login(request));
//        UserAuthException exception = Assertions.assertThrows(UserAuthException.class, () -> authService.login(request));
//        Assertions.assertEquals(String.format("User %s  not found", request.getEmail()), exception.getMessage());
//    }
}
