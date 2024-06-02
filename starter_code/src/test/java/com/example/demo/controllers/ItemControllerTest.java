package com.example.demo.controllers;

import com.example.demo.controllers.common.CommonForTest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemControllerTest {

    @Autowired
    private ItemController itemController;

    CommonForTest commonForTest;

    @Test
    public void getItems() {
        Assertions.assertEquals(HttpStatus.OK, commonForTest.getItems(itemController).getStatusCode());
    }

    @Test
    public void getItemById() {
        Assertions.assertEquals(HttpStatus.OK, commonForTest.getItemById(1L, itemController).getStatusCode());
    }

    @Test
    public void getItemsByNameSuccess() {
        Assertions.assertEquals(HttpStatus.OK, commonForTest.getItemsByName("Round Widget", itemController).getStatusCode());
    }

    @Test
    public void getItemsByNameNotFound() {
        Assertions.assertEquals(HttpStatus.NOT_FOUND, commonForTest.getItemsByName("aaaaa", itemController).getStatusCode());
    }

}
