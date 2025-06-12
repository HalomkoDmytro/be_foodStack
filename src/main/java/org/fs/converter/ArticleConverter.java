package org.fs.converter;

import org.fs.dto.ArticleDto;
import org.fs.dto.ParagraphDto;
import org.fs.entity.Article;

import java.util.List;
import java.util.stream.Collectors;

public final class ArticleConverter {

    public static ArticleDto convert(Article article) {
        ArticleDto dto = new ArticleDto();
        dto.setId(article.getId());
        dto.setTitle(article.getTitle());
        dto.setDescription(article.getDescription());
        dto.setSrcImg(article.getSrcImg());
        dto.setTheme(article.getTheme());

        List<ParagraphDto> listParagraphDto = article.getParagraph()
                .stream().map(ParagraphConverter::convert)
                .toList();
        dto.setParagraph(listParagraphDto);
        return dto;
    }

    public static Article convert(ArticleDto dto) {
        Article article = new Article();
        article.setId(dto.getId());
        article.setTitle(dto.getTitle());
        article.setDescription(dto.getDescription());
        article.setSrcImg(dto.getSrcImg());
        article.setTheme(dto.getTheme());
        article.setParagraph(dto.getParagraph().stream()
                .map(ParagraphConverter::convert)
                .collect(Collectors.toList()));
        return article;
    }

}
