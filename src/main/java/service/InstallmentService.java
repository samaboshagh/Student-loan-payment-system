package service;

import base.service.BaseEntityService;
import entity.Installment;
import entity.Student;

import java.util.List;

@SuppressWarnings("unused")
public interface InstallmentService extends BaseEntityService<Installment, Integer> {

    List<Installment> paidInstallments(Student student);

    List<Installment> unpaidInstallments(Student student);

    Double installmentCalculations();

    void payInstallment(Integer loanNumber);
}
