package com.user.controller;

import com.user.entity.User;
import com.user.exceptions.UserAlreadyExist;
import com.user.exceptions.UserNotFound;
import com.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    //Register new user
    @PostMapping("/user/register/")
    public User registerUser(@RequestBody User user) throws UserAlreadyExist {
        return userService.saveUser(user);
    }

    // Returns a user from database
    @GetMapping("/user/")
    public User getByEmail(@RequestParam String email) throws UserNotFound {
        return userService.getByEmail(email);
    }
}
