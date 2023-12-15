package repository.impl.loanCategoryImpl;

import entity.loanCategory.TuitionLoanCategory;
import entity.person.Student;
import repository.loanCategoryRepository.TuitionLoanCategoryRepository;

import javax.persistence.EntityManager;
@SuppressWarnings("unused")
public class TuitionLoanCategoryRepositoryImpl
        extends LoanCategoryRepositoryImpl<TuitionLoanCategory>
        implements TuitionLoanCategoryRepository {
    public TuitionLoanCategoryRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<TuitionLoanCategory> getEntityClass() {
        return TuitionLoanCategory.class;
    }

    @Override
    public Double academicLevelAndTheirLoanAmount(Student student) {
        return null;
    }
}
