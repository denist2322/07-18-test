package com.mysite.sbb.service;

import com.mysite.sbb.dao.ArticleRepository;
import com.mysite.sbb.domain.Article;
import com.mysite.sbb.util.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getList() {
        return articleRepository.findAll();
    }

    public Article getArticle(Integer id){
        Optional<Article> article = this.articleRepository.findById(id);
        if(article.isPresent()){
            return article.get();
        }

        else{
            throw new DataNotFoundException("Article not found");
        }
    }

    public void create(String subject, String content){
        Article article = new Article();
        article.setSubject(subject);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        article.setView(0);
        articleRepository.save(article);
    }


    public void updateView(Integer id) {
        Article article = getArticle(id);
        int cnt = article.getView();
        article.setView(cnt+1);
        articleRepository.save(article);
    }

}
