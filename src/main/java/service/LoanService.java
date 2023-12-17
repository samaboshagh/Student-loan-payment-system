package service;

import base.service.BaseEntityService;
import entity.Loan;
import entity.person.Student;

@SuppressWarnings("unused")
public interface LoanService extends BaseEntityService<Loan, Integer> {
    boolean studentHasActiveLoanForEducationalLoan(Student student);

    boolean housingDepositLoanRegistration(Student student);

    boolean tuitionLoanRegistration(Student student);

    void setLoanCategoryForEducationalLoan(Student student);

}