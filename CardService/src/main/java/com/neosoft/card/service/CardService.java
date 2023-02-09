package com.neosoft.card.service;

import com.neosoft.card.model.Card;
import com.neosoft.card.model.CardDto;

import java.util.List;

public interface CardService {
    Card save(Card card);
    List<Card> getAll();
    Card getCard(String cardNo);
    void delete(String cardNo);
    Card update(String cardNo,Card card);

    String apply(CardDto cardDto);

    Card getCardById(int id);
}
