package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCreateCart() {
        // GIVEN
        User user = new User();
        user.setUsername("Mateusz");
        userRepository.save(user);

        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setPrice(BigDecimal.TEN);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setPrice(BigDecimal.valueOf(20));
        productRepository.save(product2);

        Cart cart = new Cart();
        cart.setUser(user);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        cart.setProducts(products);

        // WHEN

        // THEN
        assertEquals(2, cart.getProducts().size());
        assertEquals(user.getUserId(), cart.getUser().getUserId());
    }

    @Test
    public void testSaveCart() {
        // GIVEN
        User user = new User();
        user.setUsername("Mateusz");
        userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);

        // WHEN
        Cart savedCart = cartRepository.save(cart);

        // THEN
        Assertions.assertNotNull(savedCart.getCartId());
    }

    @Test
    public void testFindById() {
        // GIVEN
        User user = new User();
        user.setUsername("Mateusz");
        userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        Cart savedCart = cartRepository.save(cart);

        // WHEN
        Optional<Cart> foundCart = cartRepository.findById(savedCart.getCartId());

        // THEN
        assertTrue(foundCart.isPresent());
        assertEquals(savedCart.getCartId(), foundCart.get().getCartId());
    }

    @Test
    public void testUpdateCart() {
        // GIVEN
        User user = new User();
        user.setUsername("Mateusz");
        userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

        // WHEN
        cart.setProducts(new ArrayList<>());
        cartRepository.save(cart);

        // THEN
        Cart updatedCart = cartRepository.findById(cart.getCartId()).orElse(null);
        Assertions.assertNotNull(updatedCart);
        assertTrue(updatedCart.getProducts().isEmpty());
    }

    @Test
    public void testDeleteCart() {
        // GIVEN
        User user = new User();
        user.setUsername("Mateusz");

        Cart cart = new Cart();
        cart.setUser(user);

        // WHEN
        cartRepository.delete(cart);

        // THEN
        Optional<Cart> deletedCart = cartRepository.findById(cart.getCartId());
        assertFalse(deletedCart.isPresent());
    }
    @AfterEach
    public void cleanUp() {
        cartRepository.deleteAll();
        userRepository.deleteAll();
    }
}