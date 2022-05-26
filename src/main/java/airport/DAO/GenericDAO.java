package airport.DAO;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
    T add(T t);

    Optional<T> getById(int id);

    List<T> getAll();

    List<T> getAllActive();

    T update(T t);

    boolean delete(T t);
}
