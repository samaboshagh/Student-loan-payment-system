package repository.impl.loanRepositoryImpl;

import entity.loan.HousingDepositLoan;
import repository.loanRepository.HousingDepositLoanRepository;

import javax.persistence.EntityManager;
@SuppressWarnings("unused")
public class HousingDepositLoanRepositoryImpl extends LoanRepositoryImpl<HousingDepositLoan> implements HousingDepositLoanRepository {
    public HousingDepositLoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<HousingDepositLoan> getEntityClass() {
        return HousingDepositLoan.class;
    }



}
