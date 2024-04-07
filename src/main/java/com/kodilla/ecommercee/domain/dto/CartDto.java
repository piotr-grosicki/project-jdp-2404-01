package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
public class CartDto {
    private int id;
    private int userId;
    private List<Product> products;

}
