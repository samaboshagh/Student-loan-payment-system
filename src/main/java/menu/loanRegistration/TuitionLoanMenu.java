package menu.loanRegistration;

import entity.Card;
import entity.Loan;
import entity.enumeration.LoanType;
import entity.person.Student;
import service.LoanService;
import utility.ApplicationContext;
import utility.SecurityContext;

import java.time.LocalDate;
import java.util.List;
@SuppressWarnings("unused")
public class TuitionLoanMenu {

    LoanService loanService = ApplicationContext.getLoanService();

    public void tuitionLoanRegistration(){
        Student currentUser = SecurityContext.getCurrentUser();
        LocalDate todayDate = SecurityContext.getTodayDate();
        Loan loan = new Loan();
        loan.setCreationDate(todayDate);
        studentHasActiveLoan(currentUser);
        Card card = LoanRegistrationMenu.addCardInfo();
    }

    public static void studentHasActiveLoan(Student student) {
        List<Loan> loans = student.getLoans();
        for (Loan loan : loans) {
            if (loan.getLoanCategory().getLoanType().equals(LoanType.TUITION_LOAN))
                if (loan.getCreationDate().getYear() == SecurityContext.getTodayDate().getYear())
                    System.out.println("YOU HAVE ALREADY TAKEN THIS LOAN IN CURRENT TERM");
        }
    }
}
