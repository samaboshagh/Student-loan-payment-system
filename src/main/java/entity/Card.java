package entity;

import base.entity.BaseEntity;
import entity.enumeration.BankType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Card extends BaseEntity<Integer> {

    @Column(name = "card_number", nullable = false)
    @Pattern(regexp = "^[0-9]{16}$",message = "INVALID CARD NUMBER")
    private String cardNumber;

    @Column(nullable = false)
    @Pattern(regexp = "^[0-9]{4}$",message = "INVALID CVV2 NUMBER")
    private Integer cvv2;

    @Column(name = "expires_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Column
    @Enumerated(EnumType.STRING)
    private BankType bank;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

}