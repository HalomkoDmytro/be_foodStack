package org.fs.repository;

import org.fs.entity.Article;
import org.fs.entity.ArticleView;
import org.fs.entity.ThemeArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "SELECT a.id AS id, a.title AS title, a.description AS description," +
            " a.srcImg AS srcImg, a.theme AS theme " +
            " FROM Article a WHERE a.theme = :theme",
            countQuery = "SELECT COUNT(a) FROM Article a")
    Page<ArticleView> findByTheme(@Param("theme") ThemeArticle theme, Pageable pageable);

    @Query(value = "SELECT a.id AS id, a.title AS title, a.description AS description," +
            " a.srcImg AS srcImg, a.theme AS theme " +
            " FROM Article a",
            countQuery = "SELECT COUNT(a) FROM Article a")
    Page<ArticleView> findAllArticleView(Pageable pageable);

}
