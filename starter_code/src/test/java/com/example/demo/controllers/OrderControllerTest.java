package com.example.demo.controllers;

import com.example.demo.controllers.common.CommonForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class OrderControllerTest {

    @Autowired
    private OrderController orderController;

    @Autowired
    private CartController cartController;

    @Autowired
    private UserController userController;
    CommonForTest commonForTest;

    @Test
    void submitSuccess() {
        commonForTest.createUser("hungbeo", "123456789101111", "123456789101111", userController);

        commonForTest.createCart("hungbeo", 1L, 15, cartController).getStatusCode();

        Assertions.assertEquals(HttpStatus.OK, commonForTest.submit("hungbeo", orderController).getStatusCode());
    }


    @Test
    void submitUserNotFound() {
        commonForTest.createUser("hungbeo1", "123456789101111", "123456789101111", userController);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, commonForTest.submit("123", orderController).getStatusCode());
    }

    @Test
    void getOrdersForUserSuccess() {
        commonForTest.createUser("hungbeo2", "123456789101111", "123456789101111", userController);

        commonForTest.createCart("hungbeo2", 1L, 15, cartController).getStatusCode();
        commonForTest.submit("hungbeo2", orderController);
        Assertions.assertEquals(HttpStatus.OK, commonForTest.getOrdersForUser("hungbeo2", orderController).getStatusCode());
    }

    @Test
    void getOrdersForUserNotFound() {
        commonForTest.createUser("hungbeo3", "123456789101111", "123456789101111", userController);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, commonForTest.getOrdersForUser("123", orderController).getStatusCode());
    }

}
