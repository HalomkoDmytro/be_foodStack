package org.fs.dto;

import org.fs.entity.ThemeArticle;

public record ArticleByThemeRequest(ThemeArticle theme, int page, int size, String sortBy, String sortDir) {
}
