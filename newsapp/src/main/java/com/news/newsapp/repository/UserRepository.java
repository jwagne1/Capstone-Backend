package com.news.newsapp.repository;

import com.news.newsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //to register
    boolean existsByEmail(String userEmail);

    //to Login
    User findUserByEmail(String userEmail);
}
