package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.loan.Loan;
import repository.LoanRepository;

import javax.persistence.EntityManager;

@SuppressWarnings("unused")
public class LoanRepositoryImpl extends BaseEntityRepositoryImpl<Loan,Integer> implements LoanRepository {

    public LoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }
}
