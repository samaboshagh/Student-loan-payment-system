package entity;

import base.entity.BaseEntity;
import entity.loan.Loan;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Installment extends BaseEntity<Integer> {

    @Column(name = "loan_number")
    private Integer loanNumber;

    private Double amount;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "is_paid" , columnDefinition = "boolean default false")
    private boolean isPaid;

    @ManyToOne
    private Loan loan;

}