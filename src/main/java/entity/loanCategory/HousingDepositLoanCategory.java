package entity.loanCategory;

import entity.City;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor@Getter
@Setter
@ToString
@Entity
public class HousingDepositLoanCategory extends LoanCategory {

    @OneToMany(cascade = CascadeType.ALL,mappedBy ="housingDepositLoanCategory")
    private List<City> city;

}