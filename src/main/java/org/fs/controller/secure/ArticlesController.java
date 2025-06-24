package org.fs.controller.secure;

import lombok.RequiredArgsConstructor;
import org.fs.converter.ArticleConverter;
import org.fs.dto.ArticleDto;
import org.fs.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleService articleService;

    @PostMapping("/article")
    public ArticleDto updateArticle(@RequestBody ArticleDto dto) {
        return ArticleConverter.convert(articleService.updateArticle(dto));
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

}
