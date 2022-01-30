package com.news.newsapp.controller;

import com.news.newsapp.model.Article;
import com.news.newsapp.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class ArticleController {

    private ArticleRepository articleRepository;
    private static final Logger LOGGER = Logger.getLogger(ArticleController.class.getName());

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/articles")
    public List<Article> getArticles() {
        LOGGER.info("calling getArticles method from controller");
        return articleRepository.findAll();
    }

    // http://localhost:9092/api/articles/1
    @GetMapping(path = "/articles/{articleId}")
    public String getArticle(@PathVariable Long articleId) {
        LOGGER.info("calling getArticle method from controller");
        return "getting article with id of " + articleId;
    }

    //    http://localhost:9092/api/articles
    @PostMapping(path = "/articles")
    public Article createArticle(@RequestBody Article articleObject) {
        return articleObject;
    }

    // http://localhost:9092/api/articles/1
    @PutMapping(path = "/articles/{articleId}")
    public String updateArticle(@PathVariable(value = "articleId") Long articleId, @RequestBody String body) {
        LOGGER.info("calling updateArticle method from controller");
        return "updating articles articles with id " + articleId + body;
    }

    // http://localhost:9092/api/articles/1
    @DeleteMapping("/articles/{articleId}")
    public String deleteArticle(@PathVariable(value = "articleId") Long articleId, @RequestBody String body) {
        return "deleting the article with id of " + articleId;
    }
}