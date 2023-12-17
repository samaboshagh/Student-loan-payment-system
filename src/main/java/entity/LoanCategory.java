package entity;

import base.entity.BaseEntity;
import entity.enumeration.AcademicLevel;
import entity.enumeration.LoanType;
import entity.enumeration.PaymentType;
import entity.Loan;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "loan_category")
@Entity
public class LoanCategory extends BaseEntity<Integer> {

    @Enumerated(EnumType.STRING )
    private LoanType loanType;

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