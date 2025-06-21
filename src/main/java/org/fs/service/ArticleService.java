package org.fs.service;

import lombok.RequiredArgsConstructor;
import org.fs.converter.ArticleConverter;
import org.fs.dto.ArticleDto;
import org.fs.dto.PageResponse;
import org.fs.entity.Article;
import org.fs.entity.ArticleView;
import org.fs.entity.ListGroups;
import org.fs.entity.Picture;
import org.fs.entity.ThemeArticle;
import org.fs.entity.Type;
import org.fs.excepiton.EntityNotFoundException;
import org.fs.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final S3Service s3Service;

    public Article getArticle(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article by id [" + id + "] not found."));
    }

    public ArticleDto getArticleDto(Long id) {
        return articleRepository.findById(id)
                .map(ArticleConverter::convert)
                .orElseThrow(() -> new EntityNotFoundException("Article by id [" + id + "] not found."));
    }

    public PageResponse<ArticleDto> findAllArticleView(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ArticleView> allArticleViewPage = articleRepository.findAllArticleView(pageable);
        Page<ArticleDto> articleDtoPage = allArticleViewPage.map(ArticleConverter::convert);

        return new PageResponse<>(articleDtoPage);
    }

    public PageResponse<ArticleDto> getArticlesByTheme(ThemeArticle theme, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ArticleView> articlePage = articleRepository.findByTheme(theme, pageable);
        Page<ArticleDto> articleDtoPage = articlePage.map(ArticleConverter::convert);

        return new PageResponse<>(articleDtoPage);
    }

    public PageResponse<ArticleDto> searchArticle(String search, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ArticleView> articlePage = articleRepository.search(search, pageable);
        Page<ArticleDto> articleDtoPage = articlePage.map(ArticleConverter::convert);

        return new PageResponse<>(articleDtoPage);
    }

    public Article updateArticle(Article article) {
        Article oldArticle;
        if (article.getId() != null) {
            oldArticle = getArticle(article.getId());
        } else {
            oldArticle = new Article();
        }

        oldArticle.setTitle(article.getTitle());
        oldArticle.setDescription(article.getDescription());
        oldArticle.setTheme(article.getTheme());
        oldArticle.setSrcImg(article.getSrcImg());

        oldArticle.getParagraph().clear();
        oldArticle.getParagraph().addAll(article.getParagraph());

        oldArticle.getParagraph().forEach(par -> par.setArticle(oldArticle));
        updateListGroupWithArticle(oldArticle);

        return articleRepository.save(oldArticle);
    }

    public Article updateArticle(ArticleDto dto) {
        return updateArticle(ArticleConverter.convert(dto));
    }

    public void deleteArticle(Long id) {
        Article article = getArticle(id);
        s3Service.deleteFile(article.getSrcImg());
        article.getParagraph()
                .stream()
                .filter(paragraph -> paragraph.getType().equals(Type.PICTURE))
                .map(paragraph -> (Picture) paragraph)
                .forEach(picture -> s3Service.deleteFile(picture.getData()));

        articleRepository.deleteById(id);
    }

    private void updateListGroupWithArticle(Article article) {
        article.getParagraph().stream()
                .filter(par -> par.getType().equals(Type.LIST_GROUPS))
                .map(par -> (ListGroups) par)
                .forEach(lg -> lg.getData()
                        .forEach(elem -> elem.setListGroups(lg))
                );
    }

}
