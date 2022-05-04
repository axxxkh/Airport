package Airport.DAO;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
    T add(T t);

    Optional <T> getById(int id);

    List<T> getAll();

    T update(T t);

    boolean delete(T t);
}
