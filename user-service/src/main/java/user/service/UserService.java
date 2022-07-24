package user.service;

import user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getAll();
    public Optional<User> getByEmail(String email);
    public User saveUser (User user);
}
