package org.fs.controller;

import lombok.RequiredArgsConstructor;
import org.fs.entity.Paragraph;
import org.fs.service.ParagraphService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParagraphController {

    public final ParagraphService elementArticleService;

    @GetMapping("/paragraph/{id}")
    public Paragraph getById(@PathVariable Long id) {
        return elementArticleService.getElementArticle(id);
    }

    @DeleteMapping("/paragraph/{id}")
    public void deleteById(@PathVariable Long id) {
        elementArticleService.deleteById(id);
    }

}
