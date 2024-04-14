package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderStatus;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testCreateOrder() {
        //GIVEN
        User user = new User();
        List<Product> product = new ArrayList<>();
        Order order = new Order();
        order.setId(1);
        order.setUser(user);
        order.setDateOfOrderCreation(LocalDate.of(2000, 1, 1));
        order.setStatus(OrderStatus.NEW);
        order.setProducts(product);

        //WHEN

        //THEN
        assertEquals(1, order.getId());
        assertEquals(OrderStatus.NEW, order.getStatus());
        assertEquals(LocalDate.of(2000, 1, 1), order.getDateOfOrderCreation());

    }

    @Test
    void testSaveOrder() {
        //GIVEN
        Order order = new Order(1,null,LocalDate.now(),OrderStatus.NEW, null );

        //WHEN
        Order savedOrder = orderRepository.save(order);

        //THEN
        assertNotNull(savedOrder);
        assertEquals(order, savedOrder);
    }

    @Test
    void testFindOrderById() {
        //GIVEN
        Order order = new Order(1,null,LocalDate.now(),OrderStatus.NEW, null );
        orderRepository.save(order);

        //WHEN
        Order foundOrderById = orderRepository.findById(1).orElse(null);

        //THEN
        assertNotNull(foundOrderById);
    }

    @Test
    void testUpdateOrder() {
        //GIVEN
        Order order = new Order(1,null,LocalDate.now(),OrderStatus.NEW, null );
        orderRepository.save(order);
        order.setStatus(OrderStatus.COMPLETED);

        //WHEN
        orderRepository.save(order);
        Optional<Order> updatedOrder = orderRepository.findById(1);

        //THEN
        assertTrue(updatedOrder.isPresent());
        assertEquals(OrderStatus.COMPLETED, updatedOrder.get().getStatus());
    }

    @Test
    void testDeleteOrder() {
        //GIVEN
        Order order = new Order(1,null,LocalDate.now(),OrderStatus.NEW, null );
        orderRepository.save(order);

        //WHEN
        orderRepository.delete(order);

        //THEN
        assertFalse(orderRepository.findById(1).isPresent());
        assertTrue(productRepository.findAllByOrders(order).isEmpty());
        assertTrue(userRepository.findAllByOrders(order).isEmpty());
    }
}

