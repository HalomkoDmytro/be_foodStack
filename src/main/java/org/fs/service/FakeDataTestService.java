package org.fs.service;

import lombok.RequiredArgsConstructor;
import org.fs.entity.Article;
import org.fs.entity.ThemeArticle;
import org.fs.repository.ArticleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FakeDataTestService implements CommandLineRunner {

    private final ArticleRepository articleRepository;

    @Override
    public void run(String... args) {
        articleRepository.saveAll(getList());
    }

    public List<Article> getList() {
        List<Article> list = new ArrayList<>();
        list.add(new Article(null, "title 1", "descritption 1", null, ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, "title 2", "descritption 2", "https://png.pngtree.com/png-vector/20221103/ourmid/pngtree-waffle-cone-ice-cream-isolated-refreshing-summer-dessert-sketch-png-image_6416820.png", ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, "title 3", null, "https://png.pngtree.com/png-vector/20221103/ourmid/pngtree-waffle-cone-ice-cream-isolated-refreshing-summer-dessert-sketch-png-image_6416820.png", ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, null, null, "https://png.pngtree.com/png-vector/20221103/ourmid/pngtree-waffle-cone-ice-cream-isolated-refreshing-summer-dessert-sketch-png-image_6416820.png", ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, null, "descritption 5", "https://png.pngtree.com/png-vector/20221103/ourmid/pngtree-waffle-cone-ice-cream-isolated-refreshing-summer-dessert-sketch-png-image_6416820.png", ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, "title 6", "descritption 6", "https://png.pngtree.com/png-vector/20221103/ourmid/pngtree-waffle-cone-ice-cream-isolated-refreshing-summer-dessert-sketch-png-image_6416820.png", ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, "title 7", "descritption 7", "https://png.pngtree.com/png-vector/20221103/ourmid/pngtree-waffle-cone-ice-cream-isolated-refreshing-summer-dessert-sketch-png-image_6416820.png", ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, "title 8", "descritption 8", "https://png.pngtree.com/png-vector/20240723/ourmid/pngtree-fast-food-dishes-with-drinks-and-desserts-sketch-png-image_13013852.png", ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, "title 9", "descritption 9", null, ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, "title 10", "descritption 10", null, ThemeArticle.DESSERT, Collections.emptyList()));

        return list;
    }

}
