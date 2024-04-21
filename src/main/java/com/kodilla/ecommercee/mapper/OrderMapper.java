package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public Order mapToOrder(OrderDto orderDto) {
        return new Order(
                orderDto.getOrderId(),
                null,
                orderDto.getDateOfOrderCreation(),
                orderDto.getStatus(),
                null);
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getUser().getUserId(),
                order.getDateOfOrderCreation(),
                order.getStatus(),
                order.getProducts());
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
