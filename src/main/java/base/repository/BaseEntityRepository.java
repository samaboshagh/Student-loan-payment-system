package base.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

@SuppressWarnings("unused")
public interface BaseEntityRepository<T, ID extends Serializable> {

    void saveOrUpdate(T entity);

    void delete(ID id);

    Optional<T> findById(ID id);

    Collection<T> findAll();

    boolean existsById(ID id);

    long count();

    void beginTransaction();

    void commitTransaction();

    void rollBack();

}