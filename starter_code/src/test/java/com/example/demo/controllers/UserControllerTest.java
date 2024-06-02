package com.example.demo.controllers;

import com.example.demo.controllers.common.CommonForTest;
import com.example.demo.model.persistence.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;
    CommonForTest commonForTest;

    @Test
    public void createUserSuccess() {

        Assertions.assertEquals("hungbeo",
                Objects.requireNonNull(commonForTest.createUser("hungbeo", "123456789101111", "123456789101111", userController)
                        .getBody()).getUsername());
    }

    @Test
    public void createUserFailInvalidPassword() {
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,
                commonForTest.createUser("hungbeo2", "123", "123", userController)
                        .getStatusCode());
    }

    @Test
    public void findUserByIdSuccess() {
        ResponseEntity<User> entity = commonForTest.createUser("hungbeo3", "123456789101111", "123456789101111", userController);

        Authentication authentication = new UsernamePasswordAuthenticationToken("hungbeo3", null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Assertions.assertEquals("hungbeo3", Objects.requireNonNull(commonForTest.getItemById(entity.getBody().getId(), userController)).getBody().getUsername());
    }

    @Test
    public void findUserByIdNotFound() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("hungbeo3", null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, commonForTest.getItemById(100L, userController).getStatusCode());
    }


    @Test
    public void findByUsernameSuccess() {
        ResponseEntity<User> entity = commonForTest.createUser("hungbeo4", "123456789101111", "123456789101111", userController);

        Authentication authentication = new UsernamePasswordAuthenticationToken("hungbeo4", null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Assertions.assertEquals("hungbeo4", Objects.requireNonNull(commonForTest.getItemsByName(entity.getBody().getUsername(), userController)).getBody().getUsername());
    }


    @Test
    public void findByUsernameNotFound() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("hungbeo5", null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, commonForTest.getItemsByName("100L", userController).getStatusCode());
    }

}
