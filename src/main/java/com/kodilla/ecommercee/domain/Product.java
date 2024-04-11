package com.kodilla.ecommercee.domain;

import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int productId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "GROUP_ID")
    private Group group;

    //po dodaniu encji w poniższych klasach proszę usunąc komnentarze

//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
//    private List<Cart> carts;
//
//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
//    private List<Order> orders;
}