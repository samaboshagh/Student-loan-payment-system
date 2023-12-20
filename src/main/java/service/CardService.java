package service;

import base.service.BaseEntityService;
import entity.Card;
import entity.person.Student;


@SuppressWarnings("unused")
public interface CardService extends BaseEntityService<Card, Integer> {
    Boolean isSameCard(Student student, String cardNumber);

}