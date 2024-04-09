package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.Test;


import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class UserTest {

    @Test
    public void testCreateUser() {
        //GIVEN
        User user = new User();
        user.setUserId(1);
        user.setUsername("Tomasz");
        user.setMail("tomasz@tomasz.pl");
        user.setBlocked(false);
        user.setCreationData(LocalDate.of(2000, 1, 1));
        user.setKey("2154");

        //WHEN

        //THEN
        assertEquals(1, user.getUserId());
        assertEquals("Tomasz", user.getUsername());
        assertEquals("tomasz@tomasz.pl", user.getMail());
        assertEquals(false, user.isBlocked());
        assertEquals(LocalDate.of(2000, 1, 1), user.getCreationData());
        assertEquals("2154", user.getKey());
    }

    @Test
    public void testGenKey() throws Exception {
        //GIVEN
        User user = new User();
        Method method = User.class.getDeclaredMethod("genKey");

        //WHEN
        method.setAccessible(true);
        String key = (String) method.invoke(user);

        //THEN
        assertNotNull(key);
    }

    @Test
    public void testOrdersConnections() {
        //GIVEN
        Order order = mock(Order.class);
        User user = new User();
        user.setOrders(new ArrayList<>());

        //WHEN
        user.getOrders().add(order);

        //THEN
        assertFalse(user.getOrders().isEmpty());
        assertTrue(user.getOrders().contains(order));
    }

    @Test
    public void testCartConnections() {
        //GIVEN
        Cart cart = mock(Cart.class);
        User user = new User();

        //WHEN
        user.setCart(cart);

        //THEN
        assertEquals(cart, user.getCart());
    }

}
