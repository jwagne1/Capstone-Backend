package com.news.newsapp.controller;

import com.news.newsapp.exception.InformationExistException;
import com.news.newsapp.exception.InformationNotFoundException;
import com.news.newsapp.model.Article;
import com.news.newsapp.repository.ArticleRepository;
import com.news.newsapp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class ArticleController {

    //private ArticleRepository articleRepository;
    private ArticleService articleService;
    private static final Logger LOGGER = Logger.getLogger(ArticleController.class.getName());

//    @Autowired
//    public void setArticleRepository(ArticleRepository articleRepository) {
//        this.articleRepository = articleRepository;
//    }
    @Autowired
    public void setArticleService(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public List<Article> getArticles() {
        LOGGER.info("calling getArticles method from controller");
        return articleService.getArticles();
    }

    // http://localhost:9092/api/articles/1
    @GetMapping(path = "/articles/{articleId}")
    public Optional getArticle(@PathVariable Long articleId) {
        LOGGER.info("calling getArticle method from controller");
        return articleService.getArticle(articleId);
    }

    //    http://localhost:9092/api/articles
    @PostMapping(path = "/articles")
    public Article createArticle(@RequestBody Article articleObject) {
        LOGGER.info("calling createArticle method from controller");
        return articleService.createArticle(articleObject);
    }

    // http://localhost:9092/api/articles/1
    @PutMapping(path = "/articles/{articleId}")
    public Article updateArticle(@PathVariable(value = "articleId") Long articleId, @RequestBody Article articleObject) {
        LOGGER.info("calling updateArticle method from controller");
        return articleService.updateArticle(articleId, articleObject);
    }

    // http://localhost:9092/api/articles/1
    @DeleteMapping("/articles/{articleId}")
    public Optional<Article> deleteArticle(@PathVariable(value = "articleId") Long articleId) {
        LOGGER.info("calling deleteArticle method from controller");
        return articleService.deleteArticle(articleId);
    }
}
