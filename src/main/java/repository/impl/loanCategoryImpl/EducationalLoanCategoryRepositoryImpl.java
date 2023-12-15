package repository.impl.loanCategoryImpl;

import entity.loanCategory.EducationalLoanCategory;
import entity.person.Student;
import repository.loanCategoryRepository.EducationalLoanCategoryRepository;

import javax.persistence.EntityManager;
@SuppressWarnings("unused")
public class EducationalLoanCategoryRepositoryImpl
        extends LoanCategoryRepositoryImpl<EducationalLoanCategory>
        implements EducationalLoanCategoryRepository {

    public EducationalLoanCategoryRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<EducationalLoanCategory> getEntityClass() {
        return EducationalLoanCategory.class;
    }

    @Override
    public Double academicLevelAndTheirLoanAmount(Student student) {
//        Double amount ;
//        entityManager.createQuery("""
//                UPDATE LoanCategory l SET l.amount = 1900000 WHERE l.academicLevel = KARDANI
//                """);
        return null;
    }
}
