package auth.service.impl;

import auth.dto.AuthResponse;
import auth.dto.LoginRequest;
import auth.dto.RegisterRequest;
import auth.entity.Role;
import auth.entity.User;
import auth.exceptions.UserAuthException;
import auth.exceptions.UserRegisterException;
import auth.feign.UserClient;
import auth.jwt.JwtUtil;
import auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class AuthServiceImpl implements AuthService {

    private UserClient userClient;
    private PasswordEncoder passwordEncoder;

    private JwtUtil jwtUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    public AuthResponse login(@Valid LoginRequest loginRequest) throws UserAuthException {
        Optional<User> user = userClient.getByEmail(loginRequest.getEmail());
        String errorMessage;
        if (user.isEmpty()) {
            errorMessage = String.format("User %s not found", loginRequest.getEmail());
            LOGGER.error(errorMessage);
            throw new UserAuthException(errorMessage);
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            errorMessage = String.format("Wrong password for user with email %s", loginRequest.getEmail());
            LOGGER.error(errorMessage);
            throw new UserAuthException(errorMessage);
        }
        LOGGER.info(String.format("User %s successfully sign in. Issued token ", user.get().getEmail()));
        return AuthResponse.builder()
                .email(user.get().getEmail())
                .accessToken(jwtUtil.generate(user.get()))
                .build();
    }

    public AuthResponse registerUser(@Valid RegisterRequest request) throws UserRegisterException {
        if (userClient.getByEmail(request.getEmail()).isPresent()) {
            LOGGER.error(String.format("User with email %s already exist", request.getEmail()));
            throw new UserRegisterException();
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .secretQuestion(request.getSecretQuestion())
                .secretAnswer(request.getSecretAnswer())
                .role(Role.builder().role("PASSENGER").build())
                .build();
        user = userClient.registerUser(user);
        return AuthResponse.builder()
                .email(user.getEmail())
                .accessToken(jwtUtil.generate(user))
                .build();
    }
}
