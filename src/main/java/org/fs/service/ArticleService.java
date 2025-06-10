package org.fs.service;

import lombok.RequiredArgsConstructor;
import org.fs.converter.ParagraphConverter;
import org.fs.dto.ArticleDto;
import org.fs.entity.Article;
import org.fs.entity.ThemeArticle;
import org.fs.excepiton.EntityNotFoundException;
import org.fs.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final FakeDataTestService test;

    public Article getArticle(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article by id [" + id + "] not found."));
    }

    public List<Article> getAllByTheme(ThemeArticle theme) {
        return test.getListByTheme(theme);
    }

    public Article updateArticle(ArticleDto dto) {
        Article oldArticle;
        if (dto.getId() != null) {
            oldArticle = getArticle(dto.getId());
        } else {
            oldArticle = new Article();
        }

        oldArticle.setTitle(dto.getTitle());
        oldArticle.setDescription(dto.getDescription());
        oldArticle.setTheme(dto.getTheme());

        oldArticle.setParagraph(dto.getParagraph().stream()
                .map(ParagraphConverter::convert)
                .toList());

        return articleRepository.save(oldArticle);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

}
