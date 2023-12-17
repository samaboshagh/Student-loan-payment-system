package service;

import base.service.BaseEntityService;
import entity.LoanCategory;

@SuppressWarnings("unused")
public interface LoanCategoryService extends BaseEntityService<LoanCategory, Integer> {

//    boolean isStudentQualifiedForEducationalLoan(Student student);
//
//    boolean isStudentQualifiedForTuitionLoan(Student student);

    Double academicLevelAndTheirLoanAmount();

}