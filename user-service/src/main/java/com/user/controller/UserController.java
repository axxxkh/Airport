package com.user.controller;

import com.user.entity.User;
import com.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/com/user/register/")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/com/user/")
    public Optional<User> getByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }
}
