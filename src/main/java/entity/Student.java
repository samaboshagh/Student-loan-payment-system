package entity;

import entity.enumeration.AcademicLevel;
import entity.enumeration.UniversityType;
import entity.loan.Loan;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Student extends Person {

    @Column(name = "student_number")
    private String studentNumber;

    @Column(unique = true, nullable = false)
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$" , message = "INVALID PASSWORD")
    private String password;

    @Column(name = "university_name")
    private String universityName;

    @Column(name = "university_type")
    @Enumerated(EnumType.STRING )
    private UniversityType universityType;

    @Column(name = "is_daily")
    private boolean isDaily;

    @Column(name = "academic_level")
    @Enumerated(EnumType.STRING )
    private AcademicLevel academicLevel;

    @Column(name = "has_dorm", columnDefinition = "boolean default false")
    private boolean hasDorm;

    @Column(name = "entering_year")
    @Pattern(regexp = "^[0-9]{4}$")
    private String enteringYear;

    @Column(name = "is_married", columnDefinition = "boolean default false")
    private boolean isMarried;

    @OneToOne(cascade = CascadeType.ALL)
    private StudentSpouse spouse;

    private String city;

    @Column(name = "full_address")
    private String fullAddress;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Card> cards;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Loan> loans;

}