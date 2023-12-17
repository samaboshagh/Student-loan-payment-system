package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Loan;
import entity.enumeration.LoanType;
import entity.person.Student;
import repository.LoanRepository;
import service.LoanService;
import utility.SecurityContext;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class LoanServiceImpl
        extends BaseEntityServiceImpl<Loan, Integer, LoanRepository>
        implements LoanService {

    public LoanServiceImpl(LoanRepository repository) {
        super(repository);
    }


    public boolean studentHasActiveLoanForEducationalLoan(Student student) {
        List<Loan> loans = student.getLoans();
        if (!loans.isEmpty()) {
            try {
                for (Loan loan : loans) {
                    if (loan.getLoanCategory().getLoanType().equals(LoanType.EDUCATIONAL_LOAN))
                        if (loan.getCreationDate().getYear() == SecurityContext.getTodayDate().getYear())
                            System.out.println("YOU HAVE ALREADY TAKEN THIS LOAN IN CURRENT TERM");
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    @Override
    public boolean housingDepositLoanRegistration(Student student) {

        List<Loan> loans = student.getLoans();

        if (student.isMarried()) {
            if (!student.isHasDorm()) {
                for (Loan loan : loans) {
                    if (!Objects.equals(student.getSpouse().getNationalId(), loan.getStudent().getNationalId()))
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean tuitionLoanRegistration(Student student) {
        return false;
    }

    @Override
    public void setLoanCategoryForEducationalLoan(Student student) {
        repository.setLoanCategoryForEducationalLoan(student);
    }
}