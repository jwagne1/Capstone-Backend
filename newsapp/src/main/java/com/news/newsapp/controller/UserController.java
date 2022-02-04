package com.news.newsapp.controller;

import com.news.newsapp.model.Request.LoginRequest;
import com.news.newsapp.model.User;
import com.news.newsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/auth/users")
public class UserController {
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //    http://localhost:9092/auth/users
    @PostMapping("/register")
    public User createUser(@RequestBody User userObject) {
        LOGGER.info("calling createUser method from controller");
        return userService.createUser(userObject);
    }

    //    http://localhost:9092/auth/users/login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);

    }
}
