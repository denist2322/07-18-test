package com.mysite.sbb.controller;

import com.mysite.sbb.dao.ReplyRepository;
import com.mysite.sbb.domain.Article;
import com.mysite.sbb.domain.Reply;
import com.mysite.sbb.domain.ReplyForm;
import com.mysite.sbb.service.ArticleService;
import com.mysite.sbb.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ReplyService replyService;

    @RequestMapping("list")
    @ResponseBody
    public List<Reply> showList() {

        return replyRepository.findAll();
    }

    @PostMapping("create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid ReplyForm replyForm, BindingResult bindingResult) {
        Article article = this.articleService.getArticle(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("article", article);
            return "/usr/article/article_detail";
        }

        replyService.create(article, replyForm.getContent());
        return String.format("redirect:/article/detail/%s", id);
    }

    @PostMapping("like/{articleId}/{id}/{replyLike}")
    public String likeAnswer(Model model, @PathVariable("articleId") Integer articleId, @PathVariable("id") Integer id, @PathVariable("replyLike") int replyLike) {
        if (replyLike == 0) {
            replyLike = 1;
        } else {
            replyLike = 0;
        }

        this.replyService.setLike(articleId, id, replyLike);
        return String.format("redirect:/article/detail/%s", articleId);
    }

}
