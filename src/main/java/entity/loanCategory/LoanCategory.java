package entity.loanCategory;

import base.entity.BaseEntity;
import entity.enumeration.AcademicLevel;
import entity.enumeration.PaymentType;
import entity.loan.Loan;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "loan_category")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class LoanCategory extends BaseEntity<Integer> {

    private Double amount;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING )
    private PaymentType paymentType;

    @Column(name = "academic_level")
    @Enumerated(EnumType.STRING )
    private AcademicLevel academicLevel;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "loanCategory")
    private List<Loan> loan;

}