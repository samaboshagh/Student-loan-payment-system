package menu.loanRegistration;

import entity.Card;
import entity.Loan;
import entity.person.Student;

import menu.MainMenu;
import service.LoanService;
import utility.ApplicationContext;
import utility.SecurityContext;

import java.time.LocalDate;

@SuppressWarnings("unused")
public class EducationalLoanMenu {
    LoanService loanService = ApplicationContext.getLoanService();

    public void educationalLoanRegistration() {

        Student currentUser = SecurityContext.getCurrentUser();
        LocalDate todayDate = SecurityContext.getTodayDate();
        Loan loan = new Loan();
        loan.setCreationDate(todayDate);
        loanService.setLoanCategoryForEducationalLoan(currentUser);
//        if (loanService.studentHasActiveLoanForEducationalLoan(currentUser)) {
            Card card = LoanRegistrationMenu.addCardInfo();
            loan.setStudent(currentUser);
            loanService.saveOrUpdate(loan);
            if (loan.getStudent() != null) {
                System.out.println("SUCCESSFULLY REGISTERED \n");

            } else
                System.out.println("PLEASE TRY AGAIN");
//        } else new MainMenu().start();
    }
}