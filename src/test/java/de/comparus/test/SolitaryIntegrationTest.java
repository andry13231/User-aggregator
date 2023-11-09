package de.comparus.test;

import de.comparus.test.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SolitaryIntegrationTest {


    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void getAllUsers () {
        User[] users = restTemplate.getForObject("/users", User[].class);
        Assertions.assertEquals(8, users.length);
        Assertions.assertEquals(4, Arrays.stream(users).filter(u -> u.getUsername().startsWith("1p_")).count());
        Assertions.assertEquals(4, Arrays.stream(users).filter(u -> u.getUsername().startsWith("2p_")).count());
    }

    @Test
    public void getUsersById () {
        User[] users = restTemplate.getForObject("/users/1", User[].class);
        Assertions.assertEquals(1, users.length);
        Assertions.assertEquals(1, Arrays.stream(users).filter(u -> u.getUsername().startsWith("1p_")).count());
        Assertions.assertEquals(0, Arrays.stream(users).filter(u -> u.getUsername().startsWith("2p_")).count());
    }

    @Test
    public void getUsersByName () {
        User[] users = restTemplate.getForObject("/users?username=*q*", User[].class);
        Assertions.assertEquals(3, users.length);
        Assertions.assertEquals(2, Arrays.stream(users).filter(u -> u.getUsername().startsWith("1p_")).count());
        Assertions.assertEquals(1, Arrays.stream(users).filter(u -> u.getUsername().startsWith("2p_")).count());
    }


}
