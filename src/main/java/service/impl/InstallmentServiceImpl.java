package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Installment;
import entity.person.Student;
import repository.InstallmentRepository;
import service.InstallmentService;

import java.util.List;

@SuppressWarnings("unused")
public class InstallmentServiceImpl
        extends BaseEntityServiceImpl<Installment, Integer, InstallmentRepository>
        implements InstallmentService {

    public InstallmentServiceImpl(InstallmentRepository repository) {
        super(repository);
    }

    @Override
    public List<Installment> paidInstallments(Student student) {
        return null;
    }

    @Override
    public List<Installment> unpaidInstallments(Student student) {
        return null;
    }

    @Override
    public Double installmentCalculations() {
        return null;
    }

    @Override
    public void payInstallment(Integer loanNumber) {

    }
}
