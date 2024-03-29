package com.flightService.repository;

import com.flightService.entity.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface GenericJPARepository<T extends BasicEntity> extends JpaRepository<T, Long> {

    @Override
    @Query("select e from #{#entityName} e where e.active=true")
    List<T> findAll();

    //    Find deleted
    @Query("select e from #{#entityName} e where e.active=true")
    List<T> findDeleted();

    default void safeDelete(T entity) {
        deleteById(entity.getId());
    }

    @Query("update #{#entityName} e set e.active=false where e.id = ?1")
    @Modifying
    void safeDeleteById(Long id);

    @Modifying
    default void safeDeleteAll(Iterable<? extends T> list) {
        list.forEach(this::delete);
    }

    @Query("update #{#entityName} e set e.active=false where e.id= ?1")
    @Modifying
    default void safeDeleteAll() {
    }
}
