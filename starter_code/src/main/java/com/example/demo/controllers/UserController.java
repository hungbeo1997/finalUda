package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@Transactional
@AllArgsConstructor
public class UserController {

    private final Logger logger = LogManager.getLogger(UserController.class);
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        logger.info("id = {}", id);
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            logger.error("User Not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Find Success");
        return ResponseEntity.ok(optionalUser.get());
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUserName(@PathVariable String username) {
        logger.info("username = {}", username);
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            logger.error("User Not found");
            return ResponseEntity.notFound().build();
        }

        logger.info("Find Success");
        return ResponseEntity.ok(optionalUser.get());
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
        logger.info("createUserRequest = {}", createUserRequest.toString());
        User user = new User();
        user.setUsername(createUserRequest.getUsername());
        Cart cart = new Cart();
        cartRepository.save(cart);
        user.setCart(cart);
        if (createUserRequest.getPassword().length() <= 10 ||
                !createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())) {
            logger.info("PASSWORD HAVE LENGTH > 10 char");
            logger.info("Create User - Fails");
            return ResponseEntity.badRequest().build();
        }

        user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
        userRepository.save(user);
        logger.info("Create User - Success");
        return ResponseEntity.ok(user);
    }


}
