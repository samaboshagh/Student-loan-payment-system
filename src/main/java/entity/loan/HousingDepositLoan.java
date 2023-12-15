package entity.loan;

import entity.loan.Loan;

import javax.persistence.Column;

public class HousingDepositLoan extends Loan {

    @Column(name = "rental_number")
    private String rentalNumber;

}