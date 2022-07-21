package user.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.entity.User;
import user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getAll();
    public Optional<User> getByEmail(String email);
}
