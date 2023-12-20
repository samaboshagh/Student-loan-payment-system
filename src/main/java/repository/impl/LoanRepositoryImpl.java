package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Loan;
import entity.enumeration.LoanType;
import entity.person.Student;
import repository.LoanRepository;

import javax.persistence.EntityManager;
import java.util.List;

@SuppressWarnings("unchecked")
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

    public Loan findByNationalCodeLoanHousing(String nationalCode) {
        try {
            return entityManager.createQuery("""
                                    from Loan l
                                    where l.student.nationalCode = :nationalcode and l.loanCategory.loanType = :loantype
                                    """
                            , Loan.class)
                    .setParameter("nationalcode", nationalCode)
                    .setParameter("loantype", LoanType.HOUSING_DEPOSIT_LOAN)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Double getAmount(Student student) {
        try {
            return entityManager.createQuery("""
                            SELECT l.loanCategory.amount
                            FROM Loan l WHERE l.student = :student
                            """, Double.class)
                    .setParameter("student", student)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Loan> findByStudent(Student student) {
        try {
            return entityManager.createQuery("""
                            SELECT l FROM Loan l WHERE l.student = :student
                            """)
                    .setParameter("student", student)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}