package service.impl;

import entity.enumeration.AcademicLevel;
import entity.person.Student;
import repository.StudentRepository;
import service.StudentService;

import java.time.LocalDate;

@SuppressWarnings("unused")
public class StudentServiceImpl
        extends PersonServiceImpl<Student, StudentRepository>
        implements StudentService {

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    @Override
    public Boolean isStudentGraduated(Student student, LocalDate localDate) {

        int year = localDate.getYear();

        if (student.getAcademicLevel().equals(AcademicLevel.KARDANI) || student.getAcademicLevel().equals(AcademicLevel.DISCONTINUOUS_MASTER)) {
            return student.getEnteringYear() + 2 <= year;

        } else if (student.getAcademicLevel().equals(AcademicLevel.CONTINUOUS_BACHELOR)) {
            return student.getEnteringYear() + 4 <= year;

        } else if (student.getAcademicLevel().equals(AcademicLevel.CONTINUOUS_MASTER)) {
            return student.getEnteringYear() + 6 <= year;

        } else if (student.getAcademicLevel().equals(AcademicLevel.DOCTORATE) ||
                student.getAcademicLevel().equals(AcademicLevel.CONTINUOUS_DOCTORATE) ||
                student.getAcademicLevel().equals(AcademicLevel.DISCONTINUOUS_SPECIALIZED_DOCTORATE)) {
            return student.getEnteringYear() + 5 <= year;
        }
        return false;
    }
}