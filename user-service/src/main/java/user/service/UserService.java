package user.service;

import user.entity.User;

import java.util.Optional;

public interface UserService {

    public Optional<User> getByEmail(String email);

    public User saveUser(User user);
}
