package service;

import base.service.BaseEntityService;
import entity.LoanCategory;
import entity.person.Student;

@SuppressWarnings("unused")
public interface LoanCategoryService extends BaseEntityService<LoanCategory, Integer> {

    LoanCategory findLoanCategoryForEducationLoan(Student student);

    LoanCategory findLoanCategoryForTuitionLoan(Student student);

    LoanCategory findLoanCategoryForHousingDepositLoan(Student student);

}