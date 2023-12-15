package entity.loan;

import base.entity.BaseEntity;
import entity.Installment;
import entity.loanCategory.LoanCategory;
import entity.person.Student;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
    @Enumerated(EnumType.STRING )
    private LoanCategory loanCategory;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<Installment> installations;

    private LocalDate creationDate;

    @ManyToOne
    private Student student;

}