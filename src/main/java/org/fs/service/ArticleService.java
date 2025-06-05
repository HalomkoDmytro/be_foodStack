package org.fs.service;

import lombok.RequiredArgsConstructor;
import org.fs.entity.Article;
import org.fs.excepiton.EntityNotFoundException;
import org.fs.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article getArticle(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article by id [" + id + "] not found."));
    }

    public Article updateArticle(Article article) {
        Article oldArticle;
        if (article.getId() != null) {
            oldArticle = getArticle(article.getId());
        } else {
            oldArticle = new Article();
        }

        oldArticle.setParagraph(article.getParagraph());

        return articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

}
