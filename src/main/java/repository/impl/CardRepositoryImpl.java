package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Card;
import entity.person.Student;
import repository.CardRepository;

import javax.persistence.EntityManager;

@SuppressWarnings("unused")
public class CardRepositoryImpl
        extends BaseEntityRepositoryImpl<Card, Integer>
        implements CardRepository {

    public CardRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Card> getEntityClass() {
        return Card.class;
    }

    public Boolean isSameCard(Student student, String cardNumber) {
        try {
            return entityManager.createQuery("""
                            SELECT COUNT(c.cardNumber) FROM Card c WHERE c.student = :student AND c.cardNumber = :cardNumber
                            """, Long.class)
                    .setParameter("student", student)
                    .setParameter("cardNumber", cardNumber)
                    .getSingleResult() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}