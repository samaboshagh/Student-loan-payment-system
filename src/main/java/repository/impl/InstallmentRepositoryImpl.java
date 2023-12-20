package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Installment;
import entity.Loan;
import entity.person.Student;
import repository.InstallmentRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
public class InstallmentRepositoryImpl extends BaseEntityRepositoryImpl<Installment, Integer> implements InstallmentRepository {

    public InstallmentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Installment> getEntityClass() {
        return Installment.class;
    }

    @Override
    public List<Object[]> paidInstallments(Student student) {
        try {
            List<Object[]> results = entityManager.createQuery("""
                            SELECT i.loanNumber , i.dueDate FROM Installment i
                            WHERE i.isPaid IS true AND i.loan.student = :student
                            """, Object[].class)
                    .setParameter("student", student)
                    .getResultList();
            for (Object[] result : results) {
                int numberOfInstallment = (int) result[0];
                LocalDate dueDate = (LocalDate) result[1];
            }
            return results;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Object[]> unpaidInstallments(Student student) {
        try {
            List<Object[]> results = entityManager.createQuery("""
                            SELECT i.loanNumber , i.dueDate FROM Installment i
                            WHERE i.isPaid IS false AND i.loan.student = :student
                            """, Object[].class)
                    .setParameter("student", student)
                    .getResultList();
            for (Object[] result : results) {
                int numberOfInstallment = (int) result[0];
                LocalDate dueDate = (LocalDate) result[1];
            }
            return results;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Installment findByLoanNumber(Integer loanNumber, Loan loan) {
        try {
            return entityManager.createQuery("""
                            FROM Installment i WHERE i.loanNumber = :loanNumber AND i.loan = :loan
                            """, Installment.class)
                    .setParameter("loanNumber", loanNumber)
                    .setParameter("loan", loan)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
//List<Object[]> results = entityManager.createQuery("""
//                            SELECT i.loanNumber , i.dueDate , i.amount FROM Installment i
//                            WHERE i.isPaid = false AND i.loan.student = :student
//                            """, Object[].class)
//                    .setParameter("student", student)
//                    .getResultList();
//            for (Object[] result : results) {
//                int numberOfInstallment = (int) result[0];
//                Date dueDate = (Date) result[1];
//            }