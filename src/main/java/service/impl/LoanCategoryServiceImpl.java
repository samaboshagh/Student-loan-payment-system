package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.loanCategory.LoanCategory;
import entity.person.Student;
import repository.loanCategoryRepository.LoanCategoryRepository;
import service.LoanCategoryService;

@SuppressWarnings("unused")
public class LoanCategoryServiceImpl<T extends LoanCategory , R extends LoanCategoryRepository<T>>
        extends BaseEntityServiceImpl<T, Integer, R>
        implements LoanCategoryService<T> {


    public LoanCategoryServiceImpl(R repository) {
        super(repository);
    }

    @Override
    public boolean isStudentQualifiedForEducationalLoan(Student student) {
        return false;
    }

    @Override
    public boolean isStudentQualifiedForTuitionLoan(Student student) {
        return false;
    }

    @Override
    public Double academicLevelAndTheirLoanAmount() {
        return null;
    }
}