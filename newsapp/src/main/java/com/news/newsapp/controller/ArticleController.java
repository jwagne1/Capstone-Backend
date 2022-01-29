package com.news.newsapp.controller;

import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class ArticleController {

    private static final Logger LOGGER = Logger.getLogger(ArticleController.class.getName());

    @GetMapping("/articles")
    public String getArticles() {
        return "get all articles";
    }

    // http://localhost:9092/api/articles/1
    @GetMapping(path = "/articles/{articleId}")
    public String getArticle(@PathVariable Long articleId) {
        return "getting article with id of " + articleId;
    }

//    http://localhost:9092/api/articles
    @PostMapping(path = "/articles")
    public String createArticle(@RequestBody String body) {
        return "creating articles articles " + body;
    }

    // http://localhost:9092/api/articles/1
    @PutMapping(path="/articles/{articleId}")
    public String updateArticle(@PathVariable(value = "articleId") Long articleId, @RequestBody String body){
        LOGGER.info("calling updateArticle method from controller");
        return "updating articles articles with id " + articleId + body;
    }
    // http://localhost:9092/api/articles/1
    @DeleteMapping("/articles/{articleId}")
    public String deleteArticle(@PathVariable(value = "articleId") Long articleId, @RequestBody String body){
        return "deleting the article with id of " + articleId;
    }
}
