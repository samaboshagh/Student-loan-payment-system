package repository.impl.loanRepositoryImpl;

import entity.loan.EducationalLoan;
import repository.loanRepository.EducationalLoanRepository;

import javax.persistence.EntityManager;
@SuppressWarnings("unused")
public class EducationalLoanRepositoryImpl extends LoanRepositoryImpl<EducationalLoan> implements EducationalLoanRepository {
    public EducationalLoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<EducationalLoan> getEntityClass() {
        return EducationalLoan.class;
    }
}
