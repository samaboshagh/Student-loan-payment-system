package repository;

import base.repository.BaseEntityRepository;
import entity.Installment;
import entity.Student;

import java.util.List;
@SuppressWarnings("unused")
public interface InstallmentRepository extends BaseEntityRepository<Installment,Integer> {

    List<Installment> paidInstallments(Student student);

    List<Installment> unpaidInstallments(Student student);

    void payInstallment(Integer loanNumber);

}