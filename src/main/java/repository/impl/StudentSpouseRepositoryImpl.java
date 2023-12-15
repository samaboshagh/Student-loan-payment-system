package repository.impl;

import entity.person.StudentSpouse;
import repository.StudentSpouseRepository;

import javax.persistence.EntityManager;
@SuppressWarnings("unused")
public class StudentSpouseRepositoryImpl extends PersonRepositoryImpl<StudentSpouse> implements StudentSpouseRepository {

    public StudentSpouseRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<StudentSpouse> getEntityClass() {
        return StudentSpouse.class;
    }
}