package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.LoanCategory;
import entity.person.Student;
import repository.LoanCategoryRepository;
import service.LoanCategoryService;

@SuppressWarnings("unused")
public class LoanCategoryServiceImpl
        extends BaseEntityServiceImpl<LoanCategory, Integer, LoanCategoryRepository>
        implements LoanCategoryService {

    public LoanCategoryServiceImpl(LoanCategoryRepository repository) {
        super(repository);
    }


    public LoanCategory findLoanCategoryForEducationLoan(Student student) {
        return repository.findLoanCategoryForEducationLoan(student);
    }

    @Override
    public LoanCategory findLoanCategoryForTuitionLoan(Student student) {
        return repository.findLoanCategoryForTuitionLoan(student);
    }

    @Override
    public LoanCategory findLoanCategoryForHousingDepositLoan(Student student) {
        return repository.findLoanCategoryForHousingDepositLoan(student);
    }
}