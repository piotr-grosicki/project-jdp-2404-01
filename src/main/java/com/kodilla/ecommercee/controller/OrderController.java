package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{orderId}")
    public OrderDto getOrder(@PathVariable int orderId) {
        return new OrderDto(1, 1, "2024-04-06", "PENDING", 1);
    }

    @PostMapping
    public void createOrder(@RequestBody OrderDto orderDto) {
    }

    @DeleteMapping(value = "{orderId}")
    public void deleteOrder(@PathVariable int orderId) {
    }

    @PutMapping(value = "{orderId}")
    public OrderDto updateOrder(@PathVariable int orderId, @RequestBody OrderDto orderDto) {
        return orderDto;
    }
}