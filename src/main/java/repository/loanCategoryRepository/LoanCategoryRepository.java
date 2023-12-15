package repository.loanCategoryRepository;

import base.repository.BaseEntityRepository;
import entity.loanCategory.LoanCategory;
import entity.person.Student;

@SuppressWarnings("unused")
public interface LoanCategoryRepository<T extends LoanCategory> extends BaseEntityRepository<T,Integer> {

    Double academicLevelAndTheirLoanAmount(Student student);
}
