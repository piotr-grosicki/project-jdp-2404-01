package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Cart {
    private int id;
    private int userId;
    private int cartId;
    private List<String> products;
}
