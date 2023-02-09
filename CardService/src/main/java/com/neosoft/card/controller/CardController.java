package com.neosoft.card.controller;

import com.neosoft.card.model.Card;
import com.neosoft.card.model.CardDto;
import com.neosoft.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public Card add(@RequestBody Card card){
        return cardService.save(card);
    }

    @GetMapping("/getAll")
    public List<Card> getAllCards(){
        return cardService.getAll();
    }

    @GetMapping("/get/{cardNo}")
    public Card getByCardNo(@PathVariable String cardNo){
        return cardService.getCard(cardNo);
    }

    @DeleteMapping("/delete/{cardNo}")
    public String deleteByCardNo(@PathVariable String cardNo){
        cardService.delete(cardNo);
        return "deleted";
    }

    @PutMapping("/update/{cardNo}")
    public Card update(@PathVariable String cardNo,@RequestBody Card card){
       return cardService.update(cardNo,card);
    }

    @PostMapping("/apply")
    public String apply(@RequestBody CardDto cardDto){
        return cardService.apply(cardDto);
    }
}
