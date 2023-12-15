package repository.impl.loanRepositoryImpl;

import entity.loan.TuitionLoan;
import repository.loanRepository.TuitionLoanRepository;

import javax.persistence.EntityManager;
@SuppressWarnings("unused")
public class TuitionLoanRepositoryImpl extends LoanRepositoryImpl<TuitionLoan> implements TuitionLoanRepository {
    public TuitionLoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public Class<TuitionLoan> getEntityClass() {
        return TuitionLoan.class;
    }
}
