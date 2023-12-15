package service;

import base.service.BaseEntityService;
import entity.loan.Loan;

@SuppressWarnings("unused")
public interface LoanService<T extends Loan> extends BaseEntityService<T, Integer> {
}
