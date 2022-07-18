package com.mysite.sbb.service;

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

    public void create(Article article, String content){
        Reply reply = new Reply();
        reply.setContent(content);
        reply.setCreateDate(LocalDateTime.now());
        reply.setReplyLike(0);
        reply.setArticle(article);
        replyRepository.save(reply);
    }

    public void setLike(Integer id, int replyLike) {
        Reply reply = replyRepository.findById(id).get();
        reply.setReplyLike(replyLike);
        replyRepository.save(reply);
    }
}
