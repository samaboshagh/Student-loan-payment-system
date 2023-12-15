package repository.impl.loanCategoryImpl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.loanCategory.LoanCategory;
import repository.loanCategoryRepository.LoanCategoryRepository;

import javax.persistence.EntityManager;

@SuppressWarnings("unused")
public abstract class LoanCategoryRepositoryImpl<T extends LoanCategory> extends BaseEntityRepositoryImpl<T, Integer> implements LoanCategoryRepository<T> {

    public LoanCategoryRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
