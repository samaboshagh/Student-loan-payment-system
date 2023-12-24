package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Installment;
import entity.Loan;
import entity.person.Student;
import repository.InstallmentRepository;
import service.InstallmentService;
import service.LoanService;
import utility.ApplicationContext;
import utility.SecurityContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public class InstallmentServiceImpl
        extends BaseEntityServiceImpl<Installment, Integer, InstallmentRepository>
        implements InstallmentService {

    LoanService loanService = ApplicationContext.getLoanService();

    public InstallmentServiceImpl(InstallmentRepository repository) {
        super(repository);
    }


    @Override
    public List<Object[]> paidInstallments(Student student) {
        return repository.paidInstallments(student);
    }

    @Override
    public List<Object[]> unpaidInstallments(Student student) {
        return repository.unpaidInstallments(student);
    }

    @Override
    public List<Object[]> seeUnpaidInstallmentsForEachStudent(Student student, Loan loan) {
        return repository.seeUnpaidInstallmentsForEachStudent(student,loan);
    }

    @Override
    public Installment findByLoanNumber(Integer loanNumber, Loan loan) {
        return repository.findByLoanNumber(loanNumber, loan);
    }

    @Override
    public List<Installment> fillInstallment() {

        Student student = SecurityContext.getCurrentUser();
        Loan loan = SecurityContext.getThisLoan();
        LocalDate dueDate = graduationDate();

        List<Installment> installments = new ArrayList<>();
        Double loanAmount = loan.getLoanCategory().getAmount();
        double repayAmount = (loanAmount * 4) / 100 + loanAmount;
        int count = 1;
        for (int i = 0; i < 5; i++) {
            double amount = (repayAmount / 31) * Math.pow(2, i);
            for (int j = 0; j < 12; j++) {
                Installment installment = new Installment();
                installment.setLoan(loan);
                installment.setLoanNumber(count++);
                installment.setAmount(amount / 12);
                installment.setDueDate(dueDate);
                installments.add(installment);
                dueDate = dueDate.plusMonths(1);
            }
        }
        return installments;
    }

    LocalDate graduationDate() {
        Student user = SecurityContext.getCurrentUser();
        Integer enteringYear = user.getEnteringYear();
        int graduationYear = user.getAcademicLevel().getGraduationYear() + enteringYear;
        return LocalDate.of(graduationYear, 8, 1);
    }

    @Override
    public void changeIsPaidState(Installment installment) {
        Optional.ofNullable(installment)
                .ifPresent(inst -> Optional.ofNullable(SecurityContext.getTodayDate())
                        .ifPresent(repaymentDate -> {
                            inst.setPaymentDate(repaymentDate);
                            inst.setPaid(true);
                            saveOrUpdate(inst);
                        }));
    }
}