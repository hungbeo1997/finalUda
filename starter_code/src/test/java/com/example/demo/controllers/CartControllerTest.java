package com.example.demo.controllers;


import com.example.demo.controllers.common.CommonForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class CartControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private CartController cartController;

    CommonForTest commonForTest;

    @Test
    void addToCartSuccess() {

        commonForTest.createUser("hungbeo", "123456789101111", "123456789101111", userController);

        Assertions.assertEquals(HttpStatus.OK, commonForTest.createCart("hungbeo", 1L, 15, cartController).getStatusCode());
    }

    @Test
    void addToCartNotFound() {

        commonForTest.createUser("hungbeo1", "123456789101111", "123456789101111", userController);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, commonForTest.createCart("hungdeptrai", 1L, 15, cartController).getStatusCode());
    }

    @Test
    void addToCartItemNotFound() {
        commonForTest.createUser("hungbeo2", "123456789101111", "123456789101111", userController);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, commonForTest.createCart("hungbeo2", 10L, 15, cartController).getStatusCode());
    }


    @Test
    void removeFromCartSuccess() {
        commonForTest.createUser("hungbeo3", "123456789101111", "123456789101111", userController);

        commonForTest.createCart("hungbeo3", 1L, 15, cartController);

        Assertions.assertEquals(HttpStatus.OK, commonForTest.removeCart("hungbeo3", 1L, 15, cartController).getStatusCode());
    }


    @Test
    void removeFromCartNotFound() {
        commonForTest.createUser("hungbeo5", "123456789101111", "123456789101111", userController);

        commonForTest.createCart("hungbeo5", 1L, 15, cartController);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, commonForTest.removeCart("1234", 1L, 15, cartController).getStatusCode());
    }

    @Test
    void removeFromCartItemNotFound() {
        commonForTest.createUser("hungbeo6", "123456789101111", "123456789101111", userController);

        commonForTest.createCart("hungbeo6", 1L, 15, cartController);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, commonForTest.removeCart("hungbeo6", 10L, 15, cartController).getStatusCode());
    }
}
