package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        new ArrayList<>(orders);
        return orders;
    }

    public Optional<Order> getOrderById(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    public OrderDto updateOrder(OrderDto orderDto, int orderId) {
        Order existingOrder = getOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found by id: " + orderId));
        Order updatedOrder = orderMapper.mapToOrder(orderDto);
        updatedOrder.setId(existingOrder.getId());

        Optional<User> user = userRepository.findById(orderDto.getUserId());
        if (!user.isPresent()) {
            throw new RuntimeException("User not found by id: " + orderDto.getUserId());
        }

        user.ifPresent(updatedOrder::setUser);

        Order order = orderRepository.save(updatedOrder);

        return orderMapper.mapToOrderDto(order);
    }
}
