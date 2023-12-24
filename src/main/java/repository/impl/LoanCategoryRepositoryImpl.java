package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.LoanCategory;
import entity.enumeration.LoanType;
import entity.person.Student;
import repository.LoanCategoryRepository;

import javax.persistence.EntityManager;

@SuppressWarnings("unused")
public class LoanCategoryRepositoryImpl
        extends BaseEntityRepositoryImpl<LoanCategory, Integer>
        implements LoanCategoryRepository {

    public LoanCategoryRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<LoanCategory> getEntityClass() {
        return LoanCategory.class;
    }

    @Override
    public LoanCategory findLoanCategoryForEducationLoan(Student student) {
        try {
            return entityManager.createQuery("""
                            FROM LoanCategory lc
                            WHERE lc.loanType = :loanType
                            AND lc.academicLevel IN (SELECT l.student.academicLevel FROM Loan l WHERE l.student = :student)
                            """, LoanCategory.class)
                    .setParameter("loanType", LoanType.EDUCATIONAL_LOAN)
                    .setParameter("student", student)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LoanCategory findLoanCategoryForTuitionLoan(Student student) {
        try {
            return entityManager.createQuery("""
                            FROM LoanCategory lc
                            WHERE lc.loanType = :loanType
                            AND lc.academicLevel IN (SELECT l.student.academicLevel FROM Loan l WHERE l.student = :student)
                            """, LoanCategory.class)
                    .setParameter("loanType", LoanType.TUITION_LOAN)
                    .setParameter("student", student)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public LoanCategory findLoanCategoryForHousingDepositLoan(Student student) {
        try {
            boolean isCapitalCity = student.getCity().isCapital();
            boolean isBigCity = student.getCity().isBigCity();
            return entityManager.createQuery("""
                            FROM LoanCategory lc
                            WHERE lc.loanType = :loanType
                            AND lc.isCapital = :isCapitalCity AND lc.isBigCity = :isBigCity
                            """, LoanCategory.class)
                    .setParameter("loanType", LoanType.HOUSING_DEPOSIT_LOAN)
                    .setParameter("isCapitalCity", isCapitalCity)
                    .setParameter("isBigCity", isBigCity)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}