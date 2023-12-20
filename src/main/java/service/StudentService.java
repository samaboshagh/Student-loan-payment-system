package service;

import entity.person.Student;

import java.time.LocalDate;

@SuppressWarnings("unused")
public interface StudentService extends PersonService<Student>{

    Boolean isStudentGraduated(Student student, LocalDate localDate);
}
