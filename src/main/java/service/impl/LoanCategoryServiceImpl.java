package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.LoanCategory;
import repository.LoanCategoryRepository;
import service.LoanCategoryService;

@SuppressWarnings("unused")
public class LoanCategoryServiceImpl
        extends BaseEntityServiceImpl<LoanCategory, Integer, LoanCategoryRepository>
        implements LoanCategoryService {

    public LoanCategoryServiceImpl(LoanCategoryRepository repository) {
        super(repository);
    }

    @Override
    public Double academicLevelAndTheirLoanAmount() {
        return null;
    }

//    @Override
//    public boolean isStudentQualifiedForEducationalLoan(Student student) {
//        return false;
//    }
//
//    @Override
//    public boolean isStudentQualifiedForTuitionLoan(Student student) {
//        return false;
//    }

//    @Override
//    public Double academicLevelAndTheirLoanAmount() {
//        return null;
//    }
}