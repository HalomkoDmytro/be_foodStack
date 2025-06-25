package org.fs.controller.pub;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fs.dto.ArticleByThemeRequest;
import org.fs.dto.ArticleDto;
import org.fs.dto.PageResponse;
import org.fs.dto.SearchArticleRequest;
import org.fs.service.ArticleService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticlesControllerPub {

    private final ArticleService articleService;

    @Transactional(readOnly = true)
    @GetMapping("/article/{id}")
    public ArticleDto getById(@PathVariable Long id) {
        return articleService.getArticleDto(id);
    }

    @Transactional(readOnly = true)
    @PostMapping("/article/light")
    public PageResponse<ArticleDto> getAllArticleView(@RequestBody ArticleByThemeRequest request) {
        return articleService.findAllArticleView(request.page(),
                request.size(), request.sortBy(), request.sortDir());
    }

    @Transactional(readOnly = true)
    @PostMapping("/article/theme")
    public PageResponse<ArticleDto> getList(@RequestBody @Valid ArticleByThemeRequest request) {
        return articleService.getArticlesByTheme(request.theme(), request.page(),
                request.size(), request.sortBy(), request.sortDir());
    }

    @Transactional(readOnly = true)
    @PostMapping("/article/search")
    public PageResponse<ArticleDto> searchArticle(@RequestBody @Valid SearchArticleRequest request) {
        return articleService.searchArticle(request.request(), request.page(),
                request.size(), request.sortBy(), request.sortDir());
    }

}
