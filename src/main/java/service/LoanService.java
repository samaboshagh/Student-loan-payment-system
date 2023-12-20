package service;

import base.service.BaseEntityService;
import entity.Loan;
import entity.person.Student;

import java.util.List;

public interface LoanService extends BaseEntityService<Loan, Integer> {

    boolean studentHasActiveEducationalLoan(Student student);

    boolean studentHasActiveTuitionLoan(Student student);

    boolean housingDepositLoanRegistration(Student student);

    Loan findByNationalCodeLoanHousing(String nationalCode);

    List<Loan> findByStudent(Student student);

}