package service;

import base.service.BaseEntityService;
import entity.Installment;
import entity.Loan;
import entity.person.Student;

import java.util.List;

@SuppressWarnings("unused")
public interface InstallmentService extends BaseEntityService<Installment, Integer> {

    List<Object[]> paidInstallments(Student student);

    List<Object[]> unpaidInstallments(Student student);

    List<Object[]> seeUnpaidInstallmentsForEachStudent(Student student,Loan loan);

    Installment findByLoanNumber(Integer loanNumber,Loan loan);

    List<Installment> fillInstallment();

    void changeIsPaidState(Installment installment);
}