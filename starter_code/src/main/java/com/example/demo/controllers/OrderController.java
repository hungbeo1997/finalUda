package com.example.demo.controllers;

import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
@Transactional
public class OrderController {


    private UserRepository userRepository;

    private OrderRepository orderRepository;
    private final Logger logger = LogManager.getLogger(UserController.class);

    @PostMapping("/submit/{username}")
    public ResponseEntity<UserOrder> submit(@PathVariable String username) {
        logger.info("username = {}", username);
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            logger.info("username not exist");
            return ResponseEntity.notFound().build();
        }
        UserOrder order = UserOrder.createFromCart(user.getCart());
        orderRepository.save(order);
        logger.info("Order - Success");
        return ResponseEntity.ok(order);
    }

    @GetMapping("/history/{username}")
    public ResponseEntity<List<UserOrder>> getOrdersForUser(@PathVariable String username) {
        logger.info("username = {}", username);
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            logger.info("username not exist");
            return ResponseEntity.notFound().build();
        }
        List<UserOrder> orderList = orderRepository.findByUser(user);
        logger.info("Order" + orderList);
        return ResponseEntity.ok(orderList);
    }
}
