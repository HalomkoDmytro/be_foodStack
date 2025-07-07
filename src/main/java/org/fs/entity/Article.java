package org.fs.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 1000)
    private String title;

    @Lob
    private String description;

    @Column(length = 1000)
    private String srcImg;

    @Enumerated(EnumType.STRING)
    private ThemeArticle theme;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Paragraph> paragraph = new ArrayList<>(); // todo fix delete

    public Article(Long id, String title, String description, String srcImg,
                   ThemeArticle theme, List<Paragraph> paragraph) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.srcImg = srcImg;
        this.theme = theme;
        this.paragraph = paragraph;
    }
}
