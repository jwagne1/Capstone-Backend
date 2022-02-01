package com.news.newsapp.service;

import com.news.newsapp.controller.ArticleController;
import com.news.newsapp.exception.InformationExistException;
import com.news.newsapp.exception.InformationNotFoundException;
import com.news.newsapp.model.Article;
import com.news.newsapp.repository.ArticleRepository;
import com.news.newsapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    //http: //localhost:9092/api/articles
    public List<Article> getArticles() {
        LOGGER.info("calling getArticles method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getId());
        List<Article> articles = articleRepository.findByUserId(userDetails.getUser().getId());
        if(articles.isEmpty()){
            throw new InformationNotFoundException("no articles found for user id " + userDetails.getUser().getId());
        } else {
            return articles;
        }
    }

    public Article getArticle(Long articleId) {
        LOGGER.info("calling getArticle method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Article article = articleRepository.findByIdAndUserId(articleId, userDetails.getUser().getId());
        if (article == null) {
            throw new InformationNotFoundException("article with id " + articleId + " not found");
        } else {
            return article;
        }
    }

    public Article createArticle(Article articleObject) {
        LOGGER.info("calling createArticle method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Article article = articleRepository.findByUserIdAndTitle(userDetails.getUser().getId(), articleObject.getTitle());
        if(article!=null){
            throw new InformationExistException("article with name " + article.getTitle() + " already exists");
        } else {
            articleObject.setUser(userDetails.getUser());
            return articleRepository.save(articleObject);
        }
    }

    public Article updateArticle(Long articleId, Article articleObject) {
        LOGGER.info("calling updateArticle method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getId());
        Article article = articleRepository.findByIdAndUserId(articleId, userDetails.getUser().getId());
        if (article == null) {
            throw new InformationNotFoundException("article with id " + articleId + " not found");
        } else {
            Article updateArticle = articleRepository.findById(articleId).get();
            updateArticle.setTitle(articleObject.getTitle());
            updateArticle.setDescription(articleObject.getDescription());
            updateArticle.setUrl(articleObject.getUrl());
            updateArticle.setUrlToImage(articleObject.getUrlToImage());
            return articleRepository.save(updateArticle);
        }
    }

    public Article deleteArticle(Long articleId) {
        LOGGER.info("calling deleteArticle method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getId());
        Article article = articleRepository.findByIdAndUserId(articleId, userDetails.getUser().getId());
        if (article == null) {
            throw new InformationNotFoundException("article with id " + articleId + " not found");
        } else {
            articleRepository.deleteById(articleId);
            return article;
        }
    }
}
