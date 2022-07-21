package com.mysite.sbb.service;

import com.mysite.sbb.dao.ArticleRepository;
import com.mysite.sbb.dao.ReplyRepository;
import com.mysite.sbb.domain.Reply;
import com.mysite.sbb.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public void create(Article article, String content) {
        article.setView(article.getView() - 1);
        Reply reply = new Reply();
        reply.setContent(content);
        reply.setCreateDate(LocalDateTime.now());
        reply.setReplyLike(0);
        reply.setArticle(article);
        replyRepository.save(reply);
    }

    public void setLike(Integer articleId, Integer id, int replyLike) {
        Reply reply = replyRepository.findById(id).get();
        reply.setReplyLike(replyLike);
        Article article = articleRepository.findById(articleId).get();
        article.setView(article.getView() - 1);
        replyRepository.save(reply);
        articleRepository.save(article);
    }
}
