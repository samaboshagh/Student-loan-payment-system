package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Card;
import repository.CardRepository;
import service.CardService;

@SuppressWarnings("unused")
public class CardServiceImpl
        extends BaseEntityServiceImpl<Card, Integer, CardRepository>
        implements CardService {

    public CardServiceImpl(CardRepository repository) {
        super(repository);
    }
}
