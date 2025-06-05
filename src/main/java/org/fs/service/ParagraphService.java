package org.fs.service;

import lombok.RequiredArgsConstructor;
import org.fs.entity.Paragraph;
import org.fs.excepiton.EntityNotFoundException;
import org.fs.repository.ParagraphRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParagraphService {

    private final ParagraphRepository elementArticleRepository;

    public Paragraph getElementArticle(Long id) {
        return elementArticleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ElementArticle by id [" + id + "] not found."));
    }

    public void deleteById(Long id) {
        elementArticleRepository.deleteById(id);
    }

}
