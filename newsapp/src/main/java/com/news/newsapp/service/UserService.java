package com.news.newsapp.service;

import com.news.newsapp.controller.ArticleController;
import com.news.newsapp.exception.InformationExistException;
import com.news.newsapp.model.User;
import com.news.newsapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {
    private UserRepository userRepository;
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User creatUser(User userObject){
        LOGGER.info("calling createUser method from service");
        if(!userRepository.existsByEmail(userObject.getEmail())){
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("user with email " + userObject.getEmail() + " already exists");
        }
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}
