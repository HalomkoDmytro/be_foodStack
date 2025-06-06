package org.fs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArticleUpdateDto {

    private Long id;

    private String title;

    private String description;

    private String srcImg;

    private List<ParagraphDto> paragraph = new ArrayList<>();
}
