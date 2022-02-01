package com.news.newsapp.service;

import com.news.newsapp.controller.ArticleController;
import com.news.newsapp.exception.InformationExistException;
import com.news.newsapp.model.Request.LoginRequest;
import com.news.newsapp.model.Response.LoginResponse;
import com.news.newsapp.model.User;
import com.news.newsapp.repository.UserRepository;
import com.news.newsapp.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {
    private UserRepository userRepository;
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User userObject){
        LOGGER.info("calling createUser method from service");
        if(!userRepository.existsByEmail(userObject.getEmail())){
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("user with email " + userObject.getEmail() + " already exists");
        }
    }

    public ResponseEntity<?> loginUser(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(jwt));
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}
