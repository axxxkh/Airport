package com.user.service.impl;

import com.user.entity.Role;
import com.user.entity.User;
import com.user.repository.RoleRepository;
import com.user.repository.UserRepository;
import com.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

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
