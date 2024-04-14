package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
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
    void testSaveUser() {
        //GIVEN
        User user = new User(1, "testuser", "test@test.com", false, LocalDate.now(), "1234", null, null);

        //WHEN
        User savedUser = userRepository.save(user);

        //THEN
        assertNotNull(savedUser);
        assertEquals(user, savedUser);
    }

    @Test
    void testFindUserById() {
        //GIVEN
        User user = new User(1, "testuser", "test@test.com", false, LocalDate.now(), "1234", null, null);
        userRepository.save(user);

        //WHEN
        User foundUserById = userRepository.findById(1).orElse(null);

        //THEN
        assertNotNull(foundUserById);
    }

    @Test
    void testUpdateUser() {
        //GIVEN
        User user = new User(1, "testuser", "test@test.com", false, LocalDate.now(), "1234", null, null);
        userRepository.save(user);
        user.setUsername("testuser_new");

        //WHEN
        userRepository.save(user);
        Optional<User> updatedUser = userRepository.findById(1);

        //THEN
        assertTrue(updatedUser.isPresent());
        assertEquals("testuser_new", updatedUser.get().getUsername());
    }

    @Test
    void testDeleteUser() {
        //GIVEN
        User user = new User(1, "testuser", "test@test.com", false, LocalDate.now(), "1234", null, null);
        userRepository.save(user);

        //WHEN
        userRepository.delete(user);

        //THEN
        assertFalse(userRepository.findById(1).isPresent());
        assertTrue(cartRepository.findAllByUser(user).isEmpty());
        assertTrue(orderRepository.findAllByUser(user).isEmpty());
    }
}

