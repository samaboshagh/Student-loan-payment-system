package menu.loanRegistration;

import entity.Loan;
import entity.LoanCategory;
import entity.person.Student;

import service.InstallmentService;
import service.LoanCategoryService;
import service.LoanService;
import utility.ApplicationContext;
import utility.SecurityContext;

import java.time.LocalDate;

public class EducationalLoanMenu {
    LoanService loanService = ApplicationContext.getLoanService();
    LoanCategoryService loanCategoryService = ApplicationContext.getLoanCategoryService();
    InstallmentService installmentService = ApplicationContext.getInstallmentService();

    public void educationalLoanRegistration() {

        Student currentUser = SecurityContext.getCurrentUser();
        LocalDate todayDate = SecurityContext.getTodayDate();
        Loan loan = new Loan();
        loan.setCreationDate(todayDate);
        LoanCategory category = loanCategoryService.findLoanCategoryForEducationLoan(currentUser);
        loan.setLoanCategory(category);
        if (!loanService.studentHasActiveEducationalLoan(currentUser)) {
            LoanRegistrationMenu.tuitionAndEducationLoanRegistration(currentUser, loan, loanService, installmentService);

        } else {
            System.out.println("YOU ALREADY HAVE ACTIVE LOAN ! \n");
            new LoanRegistrationMenu().chooseLoanType();
        }
    }
}