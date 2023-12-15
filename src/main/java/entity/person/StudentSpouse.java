package entity.person;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "student_spouse")
@Entity
public class StudentSpouse extends Person {

    @Column(name = "is_student",columnDefinition = "boolean default false")
    private Boolean isStudent;

}