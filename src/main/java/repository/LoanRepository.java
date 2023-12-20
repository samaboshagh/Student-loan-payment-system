package repository;

import base.repository.BaseEntityRepository;
import entity.Loan;
import entity.person.Student;

import java.util.List;

public interface LoanRepository extends BaseEntityRepository<Loan, Integer> {

    Loan findByNationalCodeLoanHousing(String nationalCode);

    Double getAmount(Student student);

    List<Loan> findByStudent(Student student);

}