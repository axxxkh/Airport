package Airport.DAO;

import java.util.List;

public interface GenericDAO<T> {
    T add(T t);
    T getById(int id);
    List<T> getAll ();
    T update (T t);
    boolean delete (T t);
}
