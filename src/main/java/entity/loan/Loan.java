package entity.loan;

import base.entity.BaseEntity;
import entity.Installment;
import entity.LoanCategory;
import entity.Student;
import entity.enumeration.PaymentType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Loan extends BaseEntity<Integer> {

    @ManyToOne
    private LoanCategory loanCategory;

    @OneToMany(mappedBy = "loan",cascade = CascadeType.ALL)
    private List<Installment> installations;

    @ManyToOne
    private Student student;

}