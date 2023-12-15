package repository.impl.loanCategoryImpl;

import entity.loanCategory.HousingDepositLoanCategory;
import entity.person.Student;
import repository.loanCategoryRepository.HousingDepositLoanCategoryRepository;

import javax.persistence.EntityManager;
@SuppressWarnings("unused")
public class HousingDepositLoanCategoryRepositoryImpl
        extends LoanCategoryRepositoryImpl<HousingDepositLoanCategory>
        implements HousingDepositLoanCategoryRepository {
    public HousingDepositLoanCategoryRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<HousingDepositLoanCategory> getEntityClass() {
        return HousingDepositLoanCategory.class;
    }

    @Override
    public Double academicLevelAndTheirLoanAmount(Student student) {
        return null;
    }
}
