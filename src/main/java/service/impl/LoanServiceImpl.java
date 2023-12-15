package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.loan.Loan;
import repository.LoanRepository;
import service.LoanService;

@SuppressWarnings("unused")
public class LoanServiceImpl
        extends BaseEntityServiceImpl<Loan, Integer, LoanRepository>
        implements LoanService {

    public LoanServiceImpl(LoanRepository repository) {
        super(repository);
    }
}
