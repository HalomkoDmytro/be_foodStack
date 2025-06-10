package org.fs.repository;

import org.fs.entity.Article;
import org.fs.entity.ThemeArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findByTheme(ThemeArticle theme, Pageable pageable);
}
