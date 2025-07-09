package org.fs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final S3Service s3Service;
    private final CashArticleService cashArticleService;

    // Constants
    private static final String ARTICLE_NOT_FOUND_MESSAGE = "Article by id [%d] not found.";
    private static final String DEFAULT_SORT_FIELD = "createdDate";
    private static final String DESC_SORT_DIRECTION = "desc";

    /**
     * Retrieves an article by ID, checking cache first
     */
    public Article getArticle(Long id) {
        validateId(id);

        Article fromCache = cashArticleService.getById(id);
        if (fromCache != null) {
            return fromCache;
        }

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ARTICLE_NOT_FOUND_MESSAGE, id)));

        cashArticleService.updateStore(article);
        return article;
    }

    /**
     * Retrieves an article DTO by ID, checking cache first
     */
    public ArticleDto getArticleDto(Long id) {
        validateId(id);

        Article fromCache = cashArticleService.getById(id);
        if (fromCache != null) {
            log.debug("Article DTO {} found in cache", id);
            return ArticleConverter.convert(fromCache);
        }

        Optional<Article> articleOptional = articleRepository.findById(id);
        articleOptional.ifPresent(cashArticleService::updateStore);

        return articleOptional
                .map(ArticleConverter::convert)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ARTICLE_NOT_FOUND_MESSAGE, id)));
    }

    /**
     * Retrieves paginated article views
     */
    public PageResponse<ArticleDto> findAllArticleView(int page, int size, String sortBy, String sortDir) {
        Pageable pageable = createPageable(page, size, sortBy, sortDir);
        Page<ArticleView> articleViewPage = articleRepository.findAllArticleView(pageable);

        return convertToPageResponse(articleViewPage);
    }

    /**
     * Retrieves paginated articles by theme
     */
    public PageResponse<ArticleDto> getArticlesByTheme(ThemeArticle theme, int page, int size, String sortBy, String sortDir) {
        validateTheme(theme);

        Pageable pageable = createPageable(page, size, sortBy, sortDir);
        Page<ArticleView> articlePage = articleRepository.findByTheme(theme, pageable);

        return convertToPageResponse(articlePage);
    }

    /**
     * Searches articles with pagination
     */
    public PageResponse<ArticleDto> searchArticle(String search, int page, int size, String sortBy, String sortDir) {
        validateSearchTerm(search);

        Pageable pageable = createPageable(page, size, sortBy, sortDir);
        Page<ArticleView> articlePage = articleRepository.search(search.trim(), pageable);

        return convertToPageResponse(articlePage);
    }

    /**
     * Updates an existing article or creates a new one
     */
    @Transactional
    public Article updateArticle(Article article) {
        validateArticle(article);

        Article targetArticle = getOrCreateArticle(article.getId());
        updateArticleFields(targetArticle, article);
        updateParagraphs(targetArticle, article);
        updateListGroupRelations(targetArticle);

        Article savedArticle = articleRepository.save(targetArticle);
        cashArticleService.updateStore(savedArticle);

        log.info("Article {} updated successfully", savedArticle.getId());
        return savedArticle;
    }

    @Transactional
    public Article updateArticle(ArticleDto dto) {
        validateArticleDto(dto);
        return updateArticle(ArticleConverter.convert(dto));
    }

    /**
     * Deletes an article and its associated files
     */
    @Transactional
    public void deleteArticle(Long id) {
        validateId(id);

        Article article = getArticle(id);

        deleteFileIfExists(article.getSrcImg());
        deleteParagraphImages(article);

        cashArticleService.removeFromCache(id);
        articleRepository.deleteById(id);
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Article ID must be positive");
        }
    }

    private void validateTheme(ThemeArticle theme) {
        if (theme == null) {
            throw new IllegalArgumentException("Theme cannot be null");
        }
    }

    private void validateSearchTerm(String search) {
        if (!StringUtils.hasText(search)) {
            throw new IllegalArgumentException("Search term cannot be empty");
        }
    }

    private void validateArticle(Article article) {
        if (article == null) {
            throw new IllegalArgumentException("Article cannot be null");
        }
        if (!StringUtils.hasText(article.getTitle())) {
            throw new IllegalArgumentException("Article title cannot be empty");
        }
    }

    private void validateArticleDto(ArticleDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Article DTO cannot be null");
        }
    }

    private Pageable createPageable(int page, int size, String sortBy, String sortDir) {
        validatePaginationParams(page, size);

        String sortField = StringUtils.hasText(sortBy) ? sortBy : DEFAULT_SORT_FIELD;
        Sort sort = DESC_SORT_DIRECTION.equalsIgnoreCase(sortDir) ?
                Sort.by(sortField).descending() : Sort.by(sortField).ascending();

        return PageRequest.of(page, size, sort);
    }

    private void validatePaginationParams(int page, int size) {
        if (page < 0) {
            throw new IllegalArgumentException("Page number must be non-negative");
        }
        if (size <= 0 || size > 100) {
            throw new IllegalArgumentException("Page size must be between 1 and 100");
        }
    }

    private PageResponse<ArticleDto> convertToPageResponse(Page<ArticleView> articleViewPage) {
        Page<ArticleDto> articleDtoPage = articleViewPage.map(ArticleConverter::convert);
        return new PageResponse<>(articleDtoPage);
    }

    private Article getOrCreateArticle(Long id) {
        return id != null ? getArticle(id) : new Article();
    }

    private void updateArticleFields(Article target, Article source) {
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setTheme(source.getTheme());
        target.setSrcImg(source.getSrcImg());
    }

    private void updateParagraphs(Article target, Article source) {
        target.getParagraph().clear();

        if (source.getParagraph() != null) {
            target.getParagraph().addAll(source.getParagraph());
            target.getParagraph().forEach(paragraph -> paragraph.setArticle(target));
        }
    }

    private void updateListGroupRelations(Article article) {
        article.getParagraph().stream()
                .filter(paragraph -> Type.LIST_GROUPS.equals(paragraph.getType()))
                .map(paragraph -> (ListGroups) paragraph)
                .forEach(this::updateListGroupElements);
    }

    private void updateListGroupElements(ListGroups listGroups) {
        if (listGroups.getData() != null) {
            listGroups.getData().forEach(element -> element.setListGroups(listGroups));
        }
    }

    private void deleteParagraphImages(Article article) {
        article.getParagraph().stream()
                .filter(paragraph -> Type.PICTURE.equals(paragraph.getType()))
                .map(paragraph -> (Picture) paragraph)
                .map(Picture::getData)
                .filter(Objects::nonNull)
                .forEach(this::deleteFileIfExists);
    }

    private void deleteFileIfExists(String filePath) {
        if (StringUtils.hasText(filePath)) {
            try {
                s3Service.deleteFile(filePath);
            } catch (Exception e) {
                log.warn("Failed to delete file: {}", filePath, e);
            }
        }
    }
}