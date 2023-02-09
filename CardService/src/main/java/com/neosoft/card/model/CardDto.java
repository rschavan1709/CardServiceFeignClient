package com.neosoft.card.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private String cardType;
    private String firstName;
    private String lastName;
    private long mobileNo;
    private String email;
    private String dob;
    private String address;
    private String panNo;
}
