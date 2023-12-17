package entity.person;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person extends BaseEntity<Integer> {

    private String firstname;

    private String lastname;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "national_code")
    @Pattern(regexp = "^[0-9]{10}$",message = "INVALID NATIONAL CODE ")
    private String nationalCode;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

}