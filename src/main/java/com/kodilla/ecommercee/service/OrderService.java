package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;

    public Order saveOrder(OrderDto orderDto) {
        return orderRepository.save(orderMapper.mapToOrder(orderDto));
    }

    public void deleteOrder(Integer orderId) throws OrderNotFoundException {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new OrderNotFoundException(String.format("Order with id %s not found", orderId));
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException(String.format("Order with id %s not found", orderId)));
    }

    public Order updateOrder(OrderDto orderDto, int orderId) throws OrderNotFoundException {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderNotFoundException(String.format("Order with id %s not found", orderId));
        }

        Order updatedOrder = orderMapper.mapToOrder(orderDto);
        updatedOrder.setId(orderId);

        Optional<User> user = userRepository.findById(orderDto.getUserId());
        if (user.isEmpty()) {
            throw new RuntimeException(String.format("User with id %s not found", orderDto.getUserId()));
        } else {
            updatedOrder.setUser(user.get());
        }

        return orderRepository.save(updatedOrder);
    }
}
