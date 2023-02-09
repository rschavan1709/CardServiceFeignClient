package com.neosoft.card.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity(name ="Card")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String cardNo;
    private String cardType;
    private String cvv;
    private String expiry;
    private String limitBalance="50000";
}
