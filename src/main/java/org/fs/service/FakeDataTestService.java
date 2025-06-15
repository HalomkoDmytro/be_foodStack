package org.fs.service;

import lombok.RequiredArgsConstructor;
import org.fs.entity.Article;
import org.fs.entity.ListGroupElement;
import org.fs.entity.ListGroups;
import org.fs.entity.Picture;
import org.fs.entity.Text;
import org.fs.entity.ThemeArticle;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FakeDataTestService implements CommandLineRunner {

    private final ArticleService articleService;

    @Override
    public void run(String... args) {
        getList().stream().forEach(article -> articleService.updateArticle(article));
    }

    public List<Article> getList() {
        List<Article> list = new ArrayList<>();
        list.add(new Article(null, "title 1", "descritption 1", null, ThemeArticle.DESSERT, List.of(getListGroups())));
        list.add(new Article(null, "title 2", "descritption 2", "https://png.pngtree.com/png-vector/20221103/ourmid/pngtree-waffle-cone-ice-cream-isolated-refreshing-summer-dessert-sketch-png-image_6416820.png", ThemeArticle.DESSERT, List.of(getText(), getText(), getPicture())));
        list.add(new Article(null, "title 3", null, "https://png.pngtree.com/png-vector/20221103/ourmid/pngtree-waffle-cone-ice-cream-isolated-refreshing-summer-dessert-sketch-png-image_6416820.png", ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, null, null, "https://png.pngtree.com/png-vector/20221103/ourmid/pngtree-waffle-cone-ice-cream-isolated-refreshing-summer-dessert-sketch-png-image_6416820.png", ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, null, "descritption 5", "https://png.pngtree.com/png-vector/20221103/ourmid/pngtree-waffle-cone-ice-cream-isolated-refreshing-summer-dessert-sketch-png-image_6416820.png", ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, "title 6", "descritption 6", "https://png.pngtree.com/png-vector/20221103/ourmid/pngtree-waffle-cone-ice-cream-isolated-refreshing-summer-dessert-sketch-png-image_6416820.png", ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, "title 7", "descritption 7", "https://png.pngtree.com/png-vector/20221103/ourmid/pngtree-waffle-cone-ice-cream-isolated-refreshing-summer-dessert-sketch-png-image_6416820.png", ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, "title 8", "descritption 8", "https://png.pngtree.com/png-vector/20240723/ourmid/pngtree-fast-food-dishes-with-drinks-and-desserts-sketch-png-image_13013852.png", ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, "title 9", "descritption 9", null, ThemeArticle.DESSERT, Collections.emptyList()));
        list.add(new Article(null, "title 10", "descritption 10", "https://png.pngtree.com/png-vector/20240723/ourmid/pngtree-fast-food-dishes-with-drinks-and-desserts-sketch-png-image_13013852.png", ThemeArticle.DESSERT, Collections.emptyList()));

        return list;
    }

    private static Picture getPicture() {
        Picture picture = new Picture();
        picture.setData("https://food-stack-file-storage.s3.eu-central-1.amazonaws.com/56752738-fd22-43b1-90ac-ff6b610d02d4-Tiramisu+z+mascarpone+kremo.jpg");
        return picture;
    }

    private static Text getText() {
        Text text = new Text();
        text.setData("Article text");
        return text;
    }

    private static ListGroups getListGroups() {
        ListGroups lg = new ListGroups();
        lg.setData(List.of(getLGE("Рецепт", null), getLGE("масло", "чуть чуть"), getLGE("батон", "2 скибки")) );
        return lg;
    }

    private static ListGroupElement getLGE(String text, String size) {
        ListGroupElement lge = new ListGroupElement();
        lge.setText(text);
        lge.setSize(size);
        return lge;
    }

}
