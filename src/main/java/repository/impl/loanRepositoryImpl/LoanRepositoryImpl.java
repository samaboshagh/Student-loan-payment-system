package repository.impl.loanRepositoryImpl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.loan.Loan;
import repository.loanRepository.LoanRepository;

import javax.persistence.EntityManager;

@SuppressWarnings("unused")
public abstract class LoanRepositoryImpl<T extends Loan> extends BaseEntityRepositoryImpl<T, Integer> implements LoanRepository<T> {


    public LoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
}