package service.impl;

import entity.person.Student;
import repository.StudentRepository;
import service.StudentService;

@SuppressWarnings("unused")
public class StudentServiceImpl extends PersonServiceImpl<Student, StudentRepository> implements StudentService {

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }
}
