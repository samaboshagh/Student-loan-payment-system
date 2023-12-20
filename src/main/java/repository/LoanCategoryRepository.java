package repository;

import base.repository.BaseEntityRepository;
import entity.LoanCategory;
import entity.person.Student;

@SuppressWarnings("unused")
public interface LoanCategoryRepository extends BaseEntityRepository<LoanCategory, Integer> {

    LoanCategory findLoanCategoryForEducationLoan(Student student);

    LoanCategory findLoanCategoryForTuitionLoan(Student student);

    LoanCategory findLoanCategoryForHousingDepositLoan(Student student);

}