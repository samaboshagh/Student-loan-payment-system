package menu.loanRegistration;

import entity.Installment;
import entity.Loan;
import entity.LoanCategory;
import entity.person.Student;
import service.InstallmentService;
import service.LoanCategoryService;
import service.LoanService;
import utility.ApplicationContext;
import utility.SecurityContext;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
public class TuitionLoanMenu {

    LoanService loanService = ApplicationContext.getLoanService();
    LoanCategoryService loanCategoryService = ApplicationContext.getLoanCategoryService();
    InstallmentService installmentService =ApplicationContext.getInstallmentService();


    public void tuitionLoanRegistration() {

        Student currentUser = SecurityContext.getCurrentUser();
        LocalDate todayDate = SecurityContext.getTodayDate();
        Loan loan = new Loan();
        loan.setCreationDate(todayDate);
        LoanCategory category = loanCategoryService.findLoanCategoryForTuitionLoan(currentUser);
        loan.setLoanCategory(category);

        if (!loanService.studentHasActiveTuitionLoan(currentUser) && !currentUser.isDaily()) {
            LoanRegistrationMenu.tuitionAndEducationLoanRegistration(currentUser, loan, loanService, installmentService);

        } else {
            System.out.println("YOU ALREADY HAVE ACTIVE LOAN ! \n");
            new LoanRegistrationMenu().chooseLoanType();
        }
    }
}