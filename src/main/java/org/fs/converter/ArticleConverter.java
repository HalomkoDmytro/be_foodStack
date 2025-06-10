package org.fs.converter;

import org.fs.dto.ArticleDto;
import org.fs.dto.ParagraphDto;
import org.fs.entity.Article;

import java.util.List;

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

}
