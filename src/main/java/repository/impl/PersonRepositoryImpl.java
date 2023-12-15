package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Person;
import repository.PersonRepository;

import javax.persistence.EntityManager;

@SuppressWarnings("unused")
public abstract class PersonRepositoryImpl<T extends Person> extends BaseEntityRepositoryImpl<T, Integer> implements PersonRepository<T> {
    public PersonRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public T findByUserName(String userName) {

        try {
            return entityManager.createQuery(
                            "SELECT u FROM "
                                    + getEntityClass().getSimpleName() +
                                    " u WHERE u.userName = :userName", getEntityClass())
                    .setParameter("userName", userName)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean existByUserNameAndPassword(String userName, String password) {

        try {
            return entityManager.createQuery(
                            "SELECT COUNT(u.userName) FROM "
                                    + getEntityClass().getSimpleName() +
                                    " u WHERE u.userName = :userName AND u.password = :password", Long.class)
                    .setParameter("userName", userName)
                    .setParameter("password", password)
                    .getSingleResult() > 0;

        } catch (Exception e) {
            System.out.println(new IllegalArgumentException("* THIS USERNAME NOT FOUND *\n").getMessage());
            return false;
        }
    }
}