package auth.service;

import auth.controller.AuthController;
import auth.dto.AuthResponse;
import auth.dto.LoginRequest;
import auth.dto.RegisterRequest;
import auth.entity.User;
import auth.exceptions.UserAuthException;
import auth.feign.UserClient;
import auth.jwt.JwtUtil;
import auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class AuthService {

    private UserRepository userRepository;
    private UserClient userClient;
    private PasswordEncoder passwordEncoder;

    private JwtUtil jwtUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    public AuthResponse login(@Valid LoginRequest loginRequest) throws UserAuthException {
        Optional<User> user = Optional.ofNullable(userClient.getByEmail(loginRequest.getEmail()).getBody());

        if (user.isEmpty()){
            LOGGER.error(String.format("User %s  not found",loginRequest.getEmail()));
            throw new UserAuthException();
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(),user.get().getPassword())) {
            LOGGER.error(String.format("Wrong password for user with email %s",loginRequest.getEmail()));
            throw new UserAuthException();
        }
            LOGGER.info(String.format("User %s successfully sign in. Issued token ",user.get().getEmail()));
           return   AuthResponse.builder()
                     .email(user.get().getEmail())
                     .accessToken(jwtUtil.generate(user.get()))
                     .build();
    }

    public AuthResponse registerRequest(@Valid RegisterRequest request) {
//        if (userRepository.isExist(request.getEmail())) {
//            throw new RuntimeException();
//        } else {
            User user = userRepository.save(User.builder()
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .build());
            return AuthResponse.builder()
                    .email(user.getEmail())
                    .accessToken(jwtUtil.generate(user))
                    .build();
//        }
    }
}
