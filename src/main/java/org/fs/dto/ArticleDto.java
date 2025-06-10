package org.fs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.fs.entity.ThemeArticle;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArticleDto {

    private Long id;

    private String title;

    private String description;

    private String srcImg;

    private ThemeArticle theme;

    private List<ParagraphDto> paragraph = new ArrayList<>();

}
