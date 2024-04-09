package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.Test;


import java.lang.reflect.Method;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
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
}
