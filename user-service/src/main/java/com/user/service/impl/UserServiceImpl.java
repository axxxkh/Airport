package com.user.service.impl;

import com.user.entity.Role;
import com.user.entity.User;
import com.user.exceptions.UserAlreadyExist;
import com.user.exceptions.UserNotFound;
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
    public User getByEmail(String email) throws UserNotFound {
        return userRepository.findByEmail(email).orElseThrow(()->new UserNotFound(String.format("User with email %s not found",email)));
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExist {
        Role role = roleRepository.findByRole(user.getRole().getRole()).orElseThrow();
        user.setRole(role);
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExist(String.format("User with email %s already exist",user.getEmail()));
        }
        return userRepository.save(user);
    }
}
