package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.loan.Loan;
import repository.loanRepository.LoanRepository;
import service.LoanService;

@SuppressWarnings("unused")
public class LoanServiceImpl<T extends Loan , R extends LoanRepository<T>>
        extends BaseEntityServiceImpl<T, Integer, R>
        implements LoanService<T> {

    public LoanServiceImpl(R repository) {
        super(repository);
    }
}
