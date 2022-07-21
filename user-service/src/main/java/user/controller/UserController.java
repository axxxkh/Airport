package user.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import user.entity.User;
import user.service.UserService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/")
    public ResponseEntity<List<User>> getAll() {

        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/user/em/")
    public ResponseEntity<?> getByEmail (@RequestParam String email) {
        return ResponseEntity.ok().body(userService.getByEmail(email));
    }
}
