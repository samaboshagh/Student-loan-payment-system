package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Installment;
import entity.Student;
import repository.InstallmentRepository;

import javax.persistence.EntityManager;
import java.util.List;

@SuppressWarnings("unused")
public class InstallmentRepositoryImpl extends BaseEntityRepositoryImpl<Installment,Integer> implements InstallmentRepository {

    public InstallmentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Installment> getEntityClass() {
        return Installment.class;
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
    public void payInstallment(Integer loanNumber) {

    }
}
