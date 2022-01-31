package com.news.newsapp.controller;

import com.news.newsapp.exception.InformationExistException;
import com.news.newsapp.exception.InformationNotFoundException;
import com.news.newsapp.model.Article;
import com.news.newsapp.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public Optional getArticle(@PathVariable Long articleId) {
        LOGGER.info("calling getArticle method from controller");
        Optional article = articleRepository.findById(articleId);
        if (article.isPresent()) {
            return article;
        } else {
            throw new InformationNotFoundException("article with id " + articleId + " not found");
        }
    }

    //    http://localhost:9092/api/articles
    @PostMapping(path = "/articles")
    public Article createArticle(@RequestBody Article articleObject) {
        LOGGER.info("calling createArticle method from controller");
        Article article = articleRepository.findByTitle(articleObject.getTitle());
        if (article != null) {
            throw new InformationExistException("article with title " + article.getTitle() + " already exists");
        } else {
            return articleRepository.save(articleObject);
        }
    }

    // http://localhost:9092/api/articles/1
    @PutMapping(path = "/articles/{articleId}")
    public Article updateArticle(@PathVariable(value = "articleId") Long articleId, @RequestBody Article articleObject) {
        LOGGER.info("calling updateArticle method from controller");
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isPresent()) {
            if (articleObject.getTitle().equals(article.get().getTitle())) {
                LOGGER.warning("category name is equal to database object name");
                throw new InformationExistException("article " + article.get().getTitle() + " already exists");
            } else {
                Article updateArticle = articleRepository.findById(articleId).get();
                updateArticle.setTitle(articleObject.getTitle());
                updateArticle.setDescription(articleObject.getDescription());
                return articleRepository.save(updateArticle);
            }
        } else {
            throw new InformationNotFoundException("article with id " + articleId + " not found");
        }
    }

    // http://localhost:9092/api/articles/1
    @DeleteMapping("/articles/{articleId}")
    public Optional<Article> deleteArticle(@PathVariable(value = "articleId") Long articleId) {
        LOGGER.info("calling deleteArticle method from controller");
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isPresent()) {
            articleRepository.deleteById(articleId);
            return article;
        } else {
            throw new InformationNotFoundException("article with id " + articleId + " not found");
        }
    }
}
