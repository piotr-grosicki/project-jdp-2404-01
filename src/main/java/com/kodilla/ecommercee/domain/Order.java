package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {


    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date_of_order_creation")
    private String dateOfOrderCreation;

    @Column(name = "status", columnDefinition = "ENUM('NEW', 'PROCESSING', 'COMPLETED', 'CANCELLED')")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "carts_items_id")
    private int cartItemsId;
}





