package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.OrderStatus;
import com.kodilla.ecommercee.domain.Product;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private int orderId;
    private int userId;
    private LocalDate dateOfOrderCreation;
    private OrderStatus status;
    private List<Product> products;
}