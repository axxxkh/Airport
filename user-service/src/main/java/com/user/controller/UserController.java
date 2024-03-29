package com.user.controller;

import com.user.entity.Role;
import com.user.entity.User;
import com.user.exceptions.UserAlreadyExist;
import com.user.exceptions.UserNotFound;
import com.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/register/")
    public User registerUser(@RequestBody User user) throws UserAlreadyExist {
        return userService.saveUser(user);
    }

    @GetMapping("/user/")
    public User getByEmail(@RequestParam String email) throws UserNotFound {
        return userService.getByEmail(email);
    }
}
