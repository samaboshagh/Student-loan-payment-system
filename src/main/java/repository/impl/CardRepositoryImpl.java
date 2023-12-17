package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Card;
import repository.CardRepository;

import javax.persistence.EntityManager;
@SuppressWarnings("unused")
public class CardRepositoryImpl
        extends BaseEntityRepositoryImpl<Card,Integer>
        implements CardRepository {
    public CardRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Card> getEntityClass() {
        return Card.class;
    }
}
