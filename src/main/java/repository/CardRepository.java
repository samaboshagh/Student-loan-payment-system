package repository;

import base.repository.BaseEntityRepository;
import entity.Card;
import entity.person.Student;

public interface CardRepository extends BaseEntityRepository<Card, Integer> {

    Boolean isSameCard(Student student, String cardNumber);

}