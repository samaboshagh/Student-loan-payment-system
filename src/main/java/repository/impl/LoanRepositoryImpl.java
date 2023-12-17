package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Loan;
import entity.person.Student;
import repository.LoanRepository;

import javax.persistence.EntityManager;

@SuppressWarnings("unused")
public class LoanRepositoryImpl
        extends BaseEntityRepositoryImpl<Loan, Integer>
        implements LoanRepository {

    public LoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }

    @Override
    public void setLoanCategoryForEducationalLoan(Student student) {
        try {
            beginTransaction();
            entityManager.createQuery("""
                            UPDATE Loan l
                            SET l.loanCategory = (SELECT lc.id FROM LoanCategory lc WHERE
                            lc.academicLevel = l.student.academicLevel)
                            WHERE l.student = :student
                            """)
                    .setParameter("student", student)
                    .executeUpdate();
            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            rollBack();
        }    }
}