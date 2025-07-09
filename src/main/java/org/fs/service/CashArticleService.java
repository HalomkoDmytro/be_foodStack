package org.fs.service;

import lombok.RequiredArgsConstructor;
import org.fs.entity.Article;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
@Scope("singleton")
@RequiredArgsConstructor
public class CashArticleService {

    private static final int MAX_CACHE_SIZE = 500;

    private final ConcurrentHashMap<Long, Article> articleCache = new ConcurrentHashMap<>();
    private final ConcurrentLinkedDeque<Article> deque = new ConcurrentLinkedDeque<>();

    public Article getById(Long id) {
        return articleCache.get(id);
    }

    public void updateStore(Article article) {
        if (!articleCache.containsKey(article.getId())) {
            deque.addLast(article);
            if (deque.size() > MAX_CACHE_SIZE) {
                Article articleInCache = deque.pollFirst();
                articleCache.remove(articleInCache.getId());
            }
        }
        articleCache.put(article.getId(), article);
    }

    public void removeFromCache(Long id) {
        articleCache.remove(id);
    }

}
