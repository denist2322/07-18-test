package com.mysite.sbb.controller;

import com.mysite.sbb.domain.Article;
import com.mysite.sbb.domain.ArticleForm;
import com.mysite.sbb.domain.ReplyForm;
import com.mysite.sbb.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("list")
    public String showList(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Article> paging = articleService.getList(page);
        model.addAttribute("paging", paging);
        return "usr/article/article_list";
    }

    @RequestMapping("detail/{id}")
    public String showDetail(@PathVariable("id") Integer id, Model model, ReplyForm articleForm) {
        Article article = this.articleService.getArticle(id);
        articleService.updateView(id);
        model.addAttribute("article", article);
        return "usr/article/article_detail";
    }

    @GetMapping("/create")
    public String articleCreate(ArticleForm articleForm) {
        return "usr/article/article_form";
    }

    @PostMapping("/create")
    public String articleCreate(@Valid ArticleForm articleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "usr/article/article_form";
        }
        this.articleService.create(articleForm.getSubject(), articleForm.getContent());
        return "redirect:/article/list";
    }
}
