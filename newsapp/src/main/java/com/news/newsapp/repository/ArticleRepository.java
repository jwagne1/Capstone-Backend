package com.news.newsapp.repository;

import com.news.newsapp.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findByTitle(String articleTitle);
}
