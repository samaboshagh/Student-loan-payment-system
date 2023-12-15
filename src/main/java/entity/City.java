package entity;

import base.entity.BaseEntity;
import entity.loanCategory.HousingDepositLoanCategory;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class City extends BaseEntity<Integer> {

    private String name;

    @Column(name = "is_capital", columnDefinition = "boolean default false")
    private boolean isCapital;

    @Column(name = "is_big_city", columnDefinition = "boolean default false")
    private boolean isBigCity;

    @ManyToOne
    private HousingDepositLoanCategory housingDepositLoanCategory;

}