package repository.impl;

import entity.person.Student;
import repository.StudentRepository;

import javax.persistence.EntityManager;
@SuppressWarnings("unused")
public class StudentRepositoryImpl extends PersonRepositoryImpl<Student> implements StudentRepository {
    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }
}