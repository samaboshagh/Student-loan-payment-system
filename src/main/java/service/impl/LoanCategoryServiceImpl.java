package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.LoanCategory;
import entity.Student;
import entity.enumeration.AcademicLevel;
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
    public boolean isStudentQualifiedForEducationalLoan(Student student) {
        return false;
    }

    @Override
    public boolean isStudentQualifiedForTuitionLoan(Student student) {
        return false;
    }

    @Override
    public Double academicLevelAndTheirLoanAmount(Student student) {
        return null;
    }
}