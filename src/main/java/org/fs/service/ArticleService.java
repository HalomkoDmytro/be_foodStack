package org.fs.service;

import lombok.RequiredArgsConstructor;
import org.fs.dto.ArticleUpdateDto;
import org.fs.entity.Article;
import org.fs.excepiton.EntityNotFoundException;
import org.fs.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article getArticle(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article by id [" + id + "] not found."));
    }

    public Article updateArticle(ArticleUpdateDto dto) {
        Article oldArticle;
        if (dto.getId() != null) {
            oldArticle = getArticle(dto.getId());
        } else {
            oldArticle = new Article();
        }
//
//        dto.getParagraph().stream()
//                .filter(paragraph -> paragraph.getType().equals(Type.PICTURE))
//                .map(img -> (PictureDto) img)
//                .forEach(img -> {
//                    try {
//                        img.setData(s3Service.upload(img.file));
//                    } catch (IOException e) {
//                        img.setData("");
//                        throw new RuntimeException(e);
//                    }
//                });

        // todo: implement update

        return articleRepository.save(oldArticle);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

}
