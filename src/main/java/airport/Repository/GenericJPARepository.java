package airport.Repository;

import airport.entity.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface GenericJPARepository<T extends BasicEntity> extends JpaRepository<T, Integer> {

    @Override
    @Query("select e from #{#entityName} e where e.active=true")
    List<T> findAll();

    //    Find deleted
    @Query("select e from #{#entityName} e where e.active=true")
    List<T> findDeleted();

    @Override
    default void delete(T entity) {
        deleteById(entity.getId());
    }

    @Override
    @Query("update #{#entityName} e set e.active=false where e.id = ?1")
    @Modifying
    void deleteById(Integer id);

    @Override
    @Modifying
    default void deleteAll(Iterable<? extends T> list) {
        list.forEach(this::delete);
    }

    @Override
    @Query("update #{#entityName} e set e.active=false where e.id= ?1")
    @Modifying
    default void deleteAll() {
    }
}
