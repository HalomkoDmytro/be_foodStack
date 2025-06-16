package org.fs.dto;

public record SearchArticleRequest(String request, int page, int size, String sortBy, String sortDir) {
}
