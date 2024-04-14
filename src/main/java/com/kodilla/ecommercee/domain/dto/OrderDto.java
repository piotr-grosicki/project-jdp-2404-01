package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private int orderId;
    private int userId;
    private String dateOfOrderCreation;
    private OrderStatus status;
    private int cartItemsId;

}