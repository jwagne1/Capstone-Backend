package com.news.newsapp.service;

import com.news.newsapp.controller.ArticleController;
import com.news.newsapp.exception.InformationExistException;
import com.news.newsapp.exception.InformationNotFoundException;
import com.news.newsapp.model.Article;
import com.news.newsapp.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;
    private static final Logger LOGGER = Logger.getLogger(ArticleController.class.getName());

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getArticles() {
        LOGGER.info("calling getArticles method from service");
        return articleRepository.findAll();
    }

    public Optional getArticle(Long articleId) {
        LOGGER.info("calling getArticle method from service");
        Optional article = articleRepository.findById(articleId);
        if (article.isPresent()) {
            return article;
        } else {
            throw new InformationNotFoundException("article with id " + articleId + " not found");
        }
    }

    public Article createArticle(Article articleObject) {
        LOGGER.info("calling createArticle method from service");
        Article article = articleRepository.findByTitle(articleObject.getTitle());
        if (article != null) {
            throw new InformationExistException("article with title " + article.getTitle() + " already exists");
        } else {
            return articleRepository.save(articleObject);
        }
    }

    public Article updateArticle(Long articleId, Article articleObject) {
        LOGGER.info("calling updateArticle method from service");
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isPresent()) {
            Article updateArticle = articleRepository.findById(articleId).get();
            updateArticle.setTitle(articleObject.getTitle());
            updateArticle.setDescription(articleObject.getDescription());
            updateArticle.setUrl(articleObject.getUrl());
            updateArticle.setUrlToImage(articleObject.getUrlToImage());
            return articleRepository.save(updateArticle);
        } else {
            throw new InformationNotFoundException("article with id " + articleId + " not found");
        }
    }

    public Optional<Article> deleteArticle(Long articleId) {
        LOGGER.info("calling deleteArticle method from service");
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isPresent()) {
            articleRepository.deleteById(articleId);
            return article;
        } else {
            throw new InformationNotFoundException("article with id " + articleId + " not found");
        }
    }
}
