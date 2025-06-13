package org.fs.converter;

import org.fs.dto.ArticleDto;
import org.fs.dto.ParagraphDto;
import org.fs.entity.Article;
import org.fs.entity.ArticleView;

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

    public static ArticleDto convert(ArticleView av) {
        ArticleDto article = new ArticleDto();
        article.setId(av.getId());
        article.setTitle(av.getTitle());
        article.setDescription(av.getDescription());
        article.setSrcImg(av.getSrcImg());
        article.setTheme(av.getTheme());
        return article;
    }

}
