package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.LoanCategory;
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
}