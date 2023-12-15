package service;

import base.service.BaseEntityService;
import entity.loanCategory.LoanCategory;
import entity.person.Student;

@SuppressWarnings("unused")
public interface LoanCategoryService<T extends LoanCategory> extends BaseEntityService<T,Integer> {

    boolean isStudentQualifiedForEducationalLoan(Student student);

    boolean isStudentQualifiedForTuitionLoan(Student student);

    Double academicLevelAndTheirLoanAmount();

}