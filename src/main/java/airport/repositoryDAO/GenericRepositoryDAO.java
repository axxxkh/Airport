package airport.repositoryDAO;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenericRepositoryDAO<T> {
    T add(T t);

    Optional<T> getById(int id);

    List<T> getAll();

    List<T> getAllActive();

    T update(T t);

    boolean delete(T t);
}
