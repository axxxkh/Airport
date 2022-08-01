package user.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import user.entity.User;
import user.service.UserService;

import java.util.Optional;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user/register/")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/user/")
    public Optional<User> getByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }
}
