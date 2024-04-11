package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
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
    private String key;

    //po dodaniu encji w poniższych klasach proszę usunąc komnentarze

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Order> orders;
//
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Cart cart;

}
