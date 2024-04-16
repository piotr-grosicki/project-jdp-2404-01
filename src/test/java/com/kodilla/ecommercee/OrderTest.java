package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static com.kodilla.ecommercee.domain.OrderStatus.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @AfterEach
    void cleanup() {
        productRepository.deleteAll();
        userRepository.deleteAll();
        orderRepository.deleteAll();
    }
    @Test
    public void testCreateOrder() {
        //GIVEN
        Order order1 = new Order();
        order1.setId(1);
        order1.setDateOfOrderCreation(LocalDate.of(2000, 1, 1));
        order1.setStatus(NEW);

        //WHEN

        //THEN
        assertEquals(1, order1.getId());
        assertEquals(NEW, order1.getStatus());
        assertEquals(LocalDate.of(2000, 1, 1), order1.getDateOfOrderCreation());

    }

    @Test
    public void testSaveOrder() {
        //GIVEN
        Order order2 = new Order(1,null,LocalDate.now(), NEW, null );

        //WHEN
        Order savedOrder = orderRepository.save(order2);

        //THEN
        assertNotNull(savedOrder);
        assertEquals(order2, savedOrder);
    }

    @Test
    public void testFindOrderById() {
        //GIVEN
        Order order3 = new Order(21,null,LocalDate.now(),NEW, null );
        orderRepository.save(order3);

        //WHEN
        Order foundOrderById = orderRepository.findById(order3.getId()).orElse(null);

        //THEN
        assertNotNull(foundOrderById);
    }

    @Test
    public void testUpdateOrder() {
        //GIVEN
        Order order4 = new Order(1,null,LocalDate.now(), NEW, null );
        orderRepository.save(order4);

        //WHEN
        order4.setStatus(COMPLETED);
        orderRepository.save(order4);
        Optional<Order> updatedOrder = orderRepository.findById(order4.getId());

        //THEN
        assertTrue(updatedOrder.isPresent());
        assertEquals(COMPLETED, updatedOrder.get().getStatus());
    }

    @Test
    public void testDeleteOrder() {
        //GIVEN
        Order order5 = new Order(1,null,LocalDate.now(), NEW, null);
        orderRepository.save(order5);

        //WHEN
        orderRepository.deleteById(order5.getId());
        Optional<Order> delOrder = orderRepository.findById(order5.getId());

        //THEN
        assertFalse(delOrder.isPresent());
        assertTrue(productRepository.findAll().isEmpty());
        assertTrue(userRepository.findAll().isEmpty());
    }

}

