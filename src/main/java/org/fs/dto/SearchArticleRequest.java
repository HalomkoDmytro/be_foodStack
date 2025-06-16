package org.fs.dto;

import jakarta.validation.constraints.Size;

public record SearchArticleRequest(
        @Size(min = 3, message = "Search request must be at least 3 characters") String request,
        int page,
        int size,
        String sortBy,
        String sortDir) {
}
