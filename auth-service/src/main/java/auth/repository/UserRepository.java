package auth.repository;

import auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Component

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
//    Boolean isExist(String username);
}

