package com.news.newsapp.repository;

import com.news.newsapp.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findByTitle(String articleTitle);

    List<Article> findByUserId(Long userId);

    Article findByUserIdAndTitle(Long userId, String articleTitle);

    Article findByIdAndUserId(Long categoryId, Long userId);
}
