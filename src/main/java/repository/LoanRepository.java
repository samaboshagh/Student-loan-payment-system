package repository;

import base.repository.BaseEntityRepository;
import entity.Loan;
import entity.person.Student;

public interface LoanRepository extends BaseEntityRepository<Loan, Integer> {

    void setLoanCategoryForEducationalLoan(Student student);

}