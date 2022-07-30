package user.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import user.entity.Role;
import user.entity.User;
import user.repository.RoleRepository;
import user.repository.UserRepository;
import user.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        Role role = roleRepository.findByRole(user.getRole().getRole()).orElseThrow();
        user.setRole(role);
        return userRepository.save(user);
    }
}
