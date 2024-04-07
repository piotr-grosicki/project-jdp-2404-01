package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class CartDto {
    private int id;
    private int userId;
    private int cartId;
    private List<String> products;

}
