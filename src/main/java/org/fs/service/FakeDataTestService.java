package org.fs.service;

import org.fs.entity.Article;
import org.fs.entity.Picture;
import org.fs.entity.Text;
import org.fs.entity.ThemeArticle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeDataTestService {

    public List<Article> getListByTheme(ThemeArticle theme) {
        return null;
    }

    private Article createArticle() {
        Article article = new Article();

        return article;
    }

    private Text createText() {
        Text text = new Text();

        return text;
    }

    private Picture createPicture() {
        Picture picture = new Picture();

        return picture;
    }
}
