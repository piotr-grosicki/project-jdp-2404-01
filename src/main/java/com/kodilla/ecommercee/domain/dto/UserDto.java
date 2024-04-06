package com.kodilla.ecommercee.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int userId;
    private String username;
    private String mail;
    private boolean blocked;
    private LocalDate creationData;
    private String key;
}
