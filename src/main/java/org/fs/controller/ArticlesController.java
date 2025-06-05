package org.fs.controller;

import lombok.RequiredArgsConstructor;
import org.fs.entity.Article;
import org.fs.service.ArticleService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleService articleService;

    @GetMapping("/article/{id}")
    public Article getById(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @PostMapping("/article")
    public Article updateArticle(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }

    @DeleteMapping("/article/{id}")
    public void deleteById(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }

}
