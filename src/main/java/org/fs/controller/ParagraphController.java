package org.fs.controller;

import lombok.RequiredArgsConstructor;
import org.fs.service.ParagraphService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParagraphController {

    private final ParagraphService paragraphService;

    @DeleteMapping("/paragraph/{id}")
    public void getById(@PathVariable Long id) {
        paragraphService.deleteParagraph(id);
    }

}
