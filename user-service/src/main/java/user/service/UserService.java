package user.service;

import user.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getByEmail(String email);

    User saveUser(User user);
}
