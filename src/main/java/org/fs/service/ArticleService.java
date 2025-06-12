package org.fs.service;

import lombok.RequiredArgsConstructor;
import org.fs.converter.ArticleConverter;
import org.fs.dto.ArticleDto;
import org.fs.dto.PageResponse;
import org.fs.entity.Article;
import org.fs.entity.ThemeArticle;
import org.fs.excepiton.EntityNotFoundException;
import org.fs.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article getArticle(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article by id [" + id + "] not found."));
    }

    public PageResponse<ArticleDto> getArticlesByTheme(ThemeArticle theme, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Article> articlePage = articleRepository.findByTheme(theme, pageable);
        Page<ArticleDto> articleDtoPage = articlePage.map(ArticleConverter::convert);

        return new PageResponse<>(articleDtoPage);
    }

    public Article updateArticle(Article article) {
        Article oldArticle;
        if (article.getId() != null) {
            oldArticle = getArticle(article.getId());
        } else {
            oldArticle = new Article();
        }

        oldArticle.setTitle(article.getTitle());
        oldArticle.setDescription(article.getDescription());
        oldArticle.setTheme(article.getTheme());

        oldArticle.setParagraph(article.getParagraph());

        oldArticle.getParagraph().forEach(par -> par.setArticle(oldArticle));

        return articleRepository.save(oldArticle);
    }

    public Article updateArticle(ArticleDto dto) {
        return updateArticle(ArticleConverter.convert(dto));
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

}
