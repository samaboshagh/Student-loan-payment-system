package entity;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate dueDate;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "is_paid" , columnDefinition = "boolean default false")
    private boolean isPaid;

    @ManyToOne
    private Loan loan;

}