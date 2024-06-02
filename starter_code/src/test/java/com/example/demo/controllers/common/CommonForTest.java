package com.example.demo.controllers.common;

import com.example.demo.controllers.CartController;
import com.example.demo.controllers.ItemController;
import com.example.demo.controllers.OrderController;
import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.requests.CreateUserRequest;
import com.example.demo.model.requests.ModifyCartRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CommonForTest {


    public static ResponseEntity<User> createUser(String name, String password, String confirmPassword, UserController userController) {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername(name);
        createUserRequest.setPassword(password);
        createUserRequest.setConfirmPassword(confirmPassword);
        return userController.createUser(createUserRequest);
    }

    public static ResponseEntity<Cart> createCart(String name, Long itemId, Integer quantity, CartController cartController) {

        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setUsername(name);
        modifyCartRequest.setItemId(itemId);
        modifyCartRequest.setQuantity(quantity);
        return cartController.addTocart(modifyCartRequest);
    }

    public static ResponseEntity<Cart> removeCart(String name, Long itemId, Integer quantity, CartController cartController) {

        ModifyCartRequest modifyCartRequestUpdate = new ModifyCartRequest();
        modifyCartRequestUpdate.setUsername(name);
        modifyCartRequestUpdate.setItemId(itemId);
        modifyCartRequestUpdate.setQuantity(quantity);
        return cartController.removeFromcart(modifyCartRequestUpdate);
    }

    public static ResponseEntity<List<Item>> getItems(ItemController itemController) {
        return itemController.getItems();
    }

    public static ResponseEntity<Item> getItemById(Long id, ItemController itemController) {
        return itemController.getItemById(id);
    }

    public static ResponseEntity<List<Item>> getItemsByName(String name, ItemController itemController) {
        return itemController.getItemsByName(name);
    }

    public static ResponseEntity<UserOrder> submit(String name, OrderController orderController) {
        return orderController.submit(name);
    }

    public static ResponseEntity<List<UserOrder>> getOrdersForUser(String name, OrderController orderController) {
        return orderController.getOrdersForUser(name);
    }

    public static ResponseEntity<User> getItemById(Long id, UserController userController) {
        return userController.findById(id);
    }

    public static ResponseEntity<User> getItemsByName(String name, UserController userController) {
        return userController.findByUserName(name);
    }
}
