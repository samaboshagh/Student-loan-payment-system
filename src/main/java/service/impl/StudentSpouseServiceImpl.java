package service.impl;

import entity.person.StudentSpouse;
import repository.StudentSpouseRepository;
import service.StudentSpouseService;

@SuppressWarnings("unused")
public class StudentSpouseServiceImpl extends PersonServiceImpl<StudentSpouse, StudentSpouseRepository> implements StudentSpouseService {

    public StudentSpouseServiceImpl(StudentSpouseRepository repository) {
        super(repository);
    }
}
