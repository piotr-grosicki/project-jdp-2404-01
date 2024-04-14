package com.kodilla.ecommercee.domain;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "USER_STATUS")
    private boolean blocked;

    @Column(name = "CREATE_USER_DATE")
    private LocalDate creationData;

    @Transient
    private String key = "1234";

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;


}
