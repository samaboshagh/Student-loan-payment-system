package repository.loanRepository;

import base.repository.BaseEntityRepository;
import entity.loan.Loan;

public interface LoanRepository<T extends Loan> extends BaseEntityRepository<T, Integer> {
}
