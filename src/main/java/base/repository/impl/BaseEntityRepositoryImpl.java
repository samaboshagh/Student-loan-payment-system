package base.repository.impl;

import base.entity.BaseEntity;
import base.repository.BaseEntityRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

@SuppressWarnings("unused")
@RequiredArgsConstructor
public abstract class BaseEntityRepositoryImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseEntityRepository<T, ID> {

    protected final EntityManager entityManager;

    @Override
    public void saveOrUpdate(T entity) {
        try {
            beginTransaction();
            saveWithoutTransaction(entity);
            commitTransaction();
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace();
            rollBack();
        }
    }


    public void saveWithoutTransaction(T entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }


    @Override
    public void delete(ID id) {
        try {
            beginTransaction();
            entityManager.createQuery("DELETE FROM " + getEntityClass().getSimpleName() + " t WHERE t.id = :id" + getEntityClass())
                    .setParameter("id", id)
                    .executeUpdate();
//            Optional<T> optional = findById(id);
//            optional.ifPresent(entityManager::remove);
            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            rollBack();
        }
    }


    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(getEntityClass(), id));
    }


    @Override
    public Collection<T> findAll() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(getEntityClass());
        Root<T> root = query.from(getEntityClass());
        query.select(root);
        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public boolean existsById(ID id) {
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(t) from " + getEntityClass().getSimpleName() + " t where t.id = :id", Long.class
        );
        query.setParameter("id", id);
        return query.getSingleResult() > 0;
    }

    @Override
    public long count() {
        return entityManager.createQuery(
                "select count(t) from " + getEntityClass().getSimpleName() + " t",
                Long.class
        ).getSingleResult();
    }

    @Override
    public void beginTransaction() {
        if (!entityManager.getTransaction().isActive())
            entityManager.getTransaction().begin();
    }

    @Override
    public void commitTransaction() {
        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();
    }

    @Override
    public void rollBack() {
        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().rollback();
    }

    public abstract Class<T> getEntityClass();

}