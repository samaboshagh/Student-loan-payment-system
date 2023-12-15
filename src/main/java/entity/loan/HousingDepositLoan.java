package entity.loan;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

@NoArgsConstructor@Getter
@Setter
@ToString
@Entity
public class HousingDepositLoan extends Loan {

    @Column(name = "rental_number")
    private String rentalNumber;

}