package com.neosoft.card.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private long mobileNo;
    private String dob;
    private String email;
    private String address;
    private String panNo;
    private int cardId;

}
