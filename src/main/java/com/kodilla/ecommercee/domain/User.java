package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int userId;
    private String username;
    private String mail;
    private boolean blocked;
    private LocalDate creationData;
    private String key;

    private String genKey() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }
}
