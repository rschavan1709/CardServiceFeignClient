package com.neosoft.card.service.impl;

import com.neosoft.card.external.service.UserService;
import com.neosoft.card.model.*;
import com.neosoft.card.repository.CardRepository;
import com.neosoft.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    UserService userService;

    public String secretKey="hello";
    public LocalDate date=LocalDate.now();
    @Override
    public Card save(Card card) {
        int min=100,max=999;
        int cvv= (int) (Math.random()*(max-min+1)+min);
        card.setCvv(String.valueOf(cvv));
        card.setExpiry(String.valueOf(date.plusYears(5)));
        Card card1=new Card();
        long small=1000000000000000L,big=9999999999999999L;
        long cardNo= (long) (Math.random()*(big-small+1)+small);
        card.setCardNo(String.valueOf(cardNo));
        String cardEncrypt= Encryption.aesEncrypt(card.getCardNo(),secretKey);
        String cvvEncrypt=Encryption.aesEncrypt(card.getCvv(),secretKey);
        String expiryEncrypt=Encryption.aesEncrypt(card.getExpiry(),secretKey);
        card1.setCardNo(cardEncrypt);
        card1.setCardType(card.getCardType());
        card1.setCvv(cvvEncrypt);
        card1.setExpiry(expiryEncrypt);
        Card card2= cardRepository.save(card1);
        if (card2 != null){
            card.setId(card2.getId());
            return card;
        }
        return null;
    }

    @Override
    public List<Card> getAll() {
        List<Card> list= cardRepository.findAll();
        for(Card card:list){
           String cardDecrypt = Decryption.aesDecrypt(card.getCardNo(),secretKey);
           String cvvDecrypt = Decryption.aesDecrypt(card.getCvv(),secretKey);
           String expiryDecrypt = Decryption.aesDecrypt(card.getExpiry(),secretKey);
           card.setCardNo(cardDecrypt);
           card.setCvv(cvvDecrypt);
           card.setExpiry(expiryDecrypt);
        }
        return list;
    }

    @Override
    public Card getCard(String cardNo) {
        List<Card> list=getAll();
        for(Card card:list){
            if(card.getCardNo().equals(cardNo)){
                return card;
            }
        }
        return null;
    }

    @Override
    public void delete(String cardNo) {
       List<Card> list= cardRepository.findAll();
       for(Card card:list){
           String cardNoDecrypt=Decryption.aesDecrypt(card.getCardNo(),secretKey);
           if(cardNoDecrypt.equals(cardNo)){
               cardRepository.delete(card);
           }
       }
    }

    @Override
    public Card update(String cardNo, Card card) {
        List<Card> list= cardRepository.findAll();
        for(Card card1:list){
            String cardNoDecrypt=Decryption.aesDecrypt(card1.getCardNo(),secretKey);
            if(cardNoDecrypt.equals(cardNo)){
                card1.setCardType(card.getCardType());
                Card card2= cardRepository.save(card1);
                if(card2 != null){
                    String cardDecrypt = Decryption.aesDecrypt(card2.getCardNo(),secretKey);
                    String cvvDecrypt = Decryption.aesDecrypt(card2.getCvv(),secretKey);
                    String expiryDecrypt = Decryption.aesDecrypt(card2.getExpiry(),secretKey);
                    card2.setCardNo(cardDecrypt);
                    card2.setCvv(cvvDecrypt);
                    card2.setExpiry(expiryDecrypt);
                    return card2;
                }
            }
        }
        return null;
    }

    @Override
    public String apply(CardDto cardDto) {
        User user= userService.getUser(cardDto.getPanNo());
        if(user == null){
            Card card=new Card();
            card.setCardType(cardDto.getCardType());
            Card card1=save(card);
            User user1=new User();
            user1.setFirstName(cardDto.getFirstName());
            user1.setLastName(cardDto.getLastName());
            user1.setMobileNo(cardDto.getMobileNo());
            user1.setDob(cardDto.getDob());
            user1.setEmail(cardDto.getEmail());
            user1.setAddress(cardDto.getAddress());
            user1.setPanNo(cardDto.getPanNo());
            user1.setCardId(card1.getId());
            User user2= userService.add(user1);
            return "Card successfully generated. Card No is: "+card1.getCardNo();
        }
        Card card= getCardById(user.getCardId());
        return "Already exists. Card No. is: "+Decryption.aesDecrypt(card.getCardNo(),secretKey);
    }

    @Override
    public Card getCardById(int id) {
        return cardRepository.findById(id).get();
    }


}
