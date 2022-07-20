package auth.service;

import auth.dto.AuthResponse;
import auth.dto.LoginRequest;
import auth.dto.RegisterRequest;
import auth.entity.User;
import auth.jwt.attemptThree.JwtUtil;
import auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
//@NoArgsConstructor
public class AuthService {
//@Autowired
    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;
//    @Autowired
    private JwtUtil jwtUtil;


    public AuthResponse login(@Valid LoginRequest loginRequest) throws RuntimeException {
        System.out.println(loginRequest);
        Optional <User> user2 = userRepository.findByEmail(loginRequest.getEmail());
        System.out.println(user2);
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException(loginRequest.getEmail()));
        return AuthResponse.builder()
                .email(user.getEmail())
                .accessToken(jwtUtil.generate(user)).build();
    }

    public AuthResponse registerRequest(@Valid RegisterRequest request) {
//        if (userRepository.isExist(request.getEmail())) {
//            throw new RuntimeException();
//        } else {
            User user = userRepository.save(User.builder()
                    .name(request.getEmail())
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
