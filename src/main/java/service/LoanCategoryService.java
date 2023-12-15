package service;

import base.service.BaseEntityService;
import entity.LoanCategory;
import entity.Student;
import entity.enumeration.AcademicLevel;

@SuppressWarnings("unused")
public interface LoanCategoryService extends BaseEntityService<LoanCategory,Integer> {

    boolean isStudentQualifiedForEducationalLoan(Student student);

    boolean isStudentQualifiedForTuitionLoan(Student student);

    Double academicLevelAndTheirLoanAmount(Student student);

}