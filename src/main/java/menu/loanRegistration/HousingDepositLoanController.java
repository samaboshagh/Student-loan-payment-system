package menu.loanRegistration;

import entity.Loan;
import entity.LoanCategory;
import entity.person.Student;
import service.LoanService;
import utility.ApplicationContext;
import utility.SecurityContext;
@SuppressWarnings("unused")
public class HousingDepositLoanController {

    LoanService loanService = ApplicationContext.getLoanService();
    Loan loan;

    public void housingDepositLoanRegistration(){
        Student student = SecurityContext.getCurrentUser();
        if (student.getAcademicLevel() != loan.getLoanCategory().getAcademicLevel()){
            if (loanService.housingDepositLoanRegistration(student)){

            }
        }
    }
}
