package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
@AllArgsConstructor
public class Cart {
    private int id;
    private int user_id;
    private final List<Product> products = new ArrayList<>();
}
