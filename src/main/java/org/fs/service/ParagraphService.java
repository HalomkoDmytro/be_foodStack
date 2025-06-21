package org.fs.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fs.entity.Article;
import org.fs.entity.Paragraph;
import org.fs.entity.Picture;
import org.fs.entity.Type;
import org.fs.excepiton.EntityNotFoundException;
import org.fs.repository.ArticleRepository;
import org.fs.repository.ListGroupsRepository;
import org.fs.repository.ParagraphRepository;
import org.fs.repository.PictureRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParagraphService {

    private final ParagraphRepository paragraphRepository;
    private final PictureRepository pictureRepository;
    private final ArticleRepository articleRepository;
//    private final ListGroupsRepository listGroupsRepository;
    private final S3Service s3Service;

    @Transactional
    public void deleteParagraph(Long id) {
        Paragraph paragraph = paragraphRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity by id [" + id + "] not found."));

        if (Type.PICTURE.equals(paragraph.getType())) {
            Picture picture = pictureRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Picture by id [" + id + "] not found."));
            s3Service.deleteFile(picture.getData());
//            pictureRepository.deleteById(id);
        }

        Article article = paragraph.getArticle();
        article.getParagraph().remove(paragraph);

        articleRepository.save(article);


//        else if (Type.LIST_GROUPS.equals(paragraph.getType())) {
//            paragraphRepository.deleteById(id);
//        }
    }

}
