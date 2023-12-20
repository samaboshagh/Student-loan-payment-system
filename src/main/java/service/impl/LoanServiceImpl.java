package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Loan;
import entity.enumeration.LoanType;
import entity.person.Student;
import repository.LoanRepository;
import service.LoanCategoryService;
import service.LoanService;
import utility.ApplicationContext;
import utility.SecurityContext;

import java.util.List;

@SuppressWarnings("unused")
public class LoanServiceImpl
        extends BaseEntityServiceImpl<Loan, Integer, LoanRepository>
        implements LoanService {

    public LoanServiceImpl(LoanRepository repository) {
        super(repository);
    }

    LoanCategoryService loanCategoryService = ApplicationContext.getLoanCategoryService();

    @Override
    public boolean studentHasActiveEducationalLoan(Student student) {
        List<Loan> loans = student.getLoans();
        student = SecurityContext.getCurrentUser();
        if (!loans.isEmpty()) {
            try {
                for (Loan loan : loans) {
                    if (loanCategoryService.findLoanCategoryForEducationLoan(student).getLoanType().equals(LoanType.EDUCATIONAL_LOAN)) {
                        if (loan.getCreationDate().getYear() == SecurityContext.getTodayDate().getYear())
                            return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean studentHasActiveTuitionLoan(Student student) {
        List<Loan> loans = student.getLoans();
        student = SecurityContext.getCurrentUser();
        if (!loans.isEmpty()) {
            try {
                for (Loan loan : loans) {
                    if (loanCategoryService.findLoanCategoryForEducationLoan(student).getLoanType().equals(LoanType.TUITION_LOAN)) {
                        if (loan.getCreationDate().getYear() == SecurityContext.getTodayDate().getYear())
                            return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean housingDepositLoanRegistration(Student student) {
        List<Loan> loans = student.getLoans();
        boolean hasLoanBefore = false;
        boolean spouseHasLoanBefore = false;
        if (!loans.isEmpty()) {
            for (Loan l : loans) {
                if (l.getLoanCategory().getLoanType().equals(LoanType.HOUSING_DEPOSIT_LOAN)) {
                    if (l.getLoanCategory().getAcademicLevel() == student.getAcademicLevel()) {
                        hasLoanBefore = true;
                        break;
                    }
                }
            }
            for (Loan loan : loans) {
                if (findByNationalCodeLoanHousing(student.getSpouse().getNationalCode()) != null) {
                    spouseHasLoanBefore = true;
                    break;
                }
            }
        }
        return !hasLoanBefore && student.isMarried() && !student.isHasDorm() && !spouseHasLoanBefore;
    }

    @Override
    public Loan findByNationalCodeLoanHousing(String nationalCode) {
        return repository.findByNationalCodeLoanHousing(nationalCode);
    }

    @Override
    public List<Loan> findByStudent(Student student) {
        return repository.findByStudent(student);
    }

}