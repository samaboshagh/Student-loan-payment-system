package entity;

import base.entity.BaseEntity;
import entity.person.Student;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Loan extends BaseEntity<Integer> {

    @ManyToOne(cascade = CascadeType.ALL)
    @Enumerated(EnumType.STRING )
    private LoanCategory loanCategory;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<Installment> installations;

    private LocalDate creationDate;

    @Column(name = "rental_number")
    private String rentalNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;


    @Override
    public String toString() {
        return "Loan{" +
                "id  = " + getId() +
                " ,loanCategory  = " + loanCategory +
                ", creationDate  = " + creationDate +
                ", student       = " + student +
                '}';
    }
}