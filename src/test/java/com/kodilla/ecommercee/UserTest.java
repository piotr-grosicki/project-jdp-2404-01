package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;



    @Test
    public void testCreateUser() {
        //GIVEN
        User user1 = new User();
        user1.setUserId(1);
        user1.setUsername("Tomasz");
        user1.setMail("tomasz@tomasz.pl");
        user1.setBlocked(false);
        user1.setCreationData(LocalDate.of(2000, 1, 1));
        user1.setKey("2154");

        //WHEN

        //THEN
        assertEquals(1, user1.getUserId());
        assertEquals("Tomasz", user1.getUsername());
        assertEquals("tomasz@tomasz.pl", user1.getMail());
        assertEquals(false, user1.isBlocked());
        assertEquals(LocalDate.of(2000, 1, 1), user1.getCreationData());
        assertEquals("2154", user1.getKey());
    }

    @Test
    public void testSaveUser() {
        //GIVEN
        User user2 = new User(1, "testuser", "test@test.com", false, LocalDate.now(), "1234", null, null);

        //WHEN
        User savedUser = userRepository.save(user2);

        //THEN
        assertNotNull(savedUser);
        assertEquals(user2, savedUser);
    }

    @Test
    public void testFindUserById() {
        //GIVEN
        User user3 = new User(1, "testuser", "test@test.com", false, LocalDate.now(), "1234", null, null);
        userRepository.save(user3);

        //WHEN
        User foundUserById = userRepository.findById(user3.getUserId()).orElse(null);

        //THEN
        assertNotNull(foundUserById);
    }

    @Test
    public void testUpdateUser() {
        //GIVEN
        User user4 = new User(1, "testuser", "test@test.com", false, LocalDate.now(), "1234", null, null);
        userRepository.save(user4);
        user4.setUsername("testuser_new");

        //WHEN
        userRepository.save(user4);
        Optional<User> updatedUser = userRepository.findById(user4.getUserId());

        //THEN
        assertTrue(updatedUser.isPresent());
        assertEquals("testuser_new", updatedUser.get().getUsername());
    }

    @Test
    public void testDeleteUser() {
        //GIVEN
        User user5 = new User(1, "testuser", "test@test.com", false, LocalDate.now(), "1234", null, null);
        userRepository.save(user5);

        //WHEN
        userRepository.deleteById(user5.getUserId());
        Optional<User> delUser = userRepository.findById(user5.getUserId());

        //THEN
        assertFalse(delUser.isPresent());
        assertTrue(cartRepository.findAll().isEmpty());
        assertTrue(orderRepository.findAll().isEmpty());
    }
}

