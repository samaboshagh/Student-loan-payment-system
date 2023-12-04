package base.service.impl;

import base.entity.BaseEntity;
import base.repository.BaseEntityRepository;
import base.service.BaseEntityService;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

@SuppressWarnings("unused")
@RequiredArgsConstructor
public class BaseEntityServiceImpl<T extends BaseEntity<ID>, ID extends Serializable, R extends BaseEntityRepository<T, ID>> implements BaseEntityService<T, ID> {

    protected final R repository;

    @Override
    public void saveOrUpdate(T entity) {
        repository.saveOrUpdate(entity);
    }

    @Override
    public void delete(ID id) {
        repository.delete(id);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public Collection<T> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void beginTransaction() {
        repository.beginTransaction();
    }

    @Override
    public void commitTransaction() {
        repository.commitTransaction();
    }

    @Override
    public void rollBack() {
        repository.rollBack();
    }
}