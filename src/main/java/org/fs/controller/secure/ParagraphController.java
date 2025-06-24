package org.fs.controller.secure;

import lombok.RequiredArgsConstructor;
import org.fs.service.ParagraphService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
@RequiredArgsConstructor
public class ParagraphController {

    private final ParagraphService paragraphService;

    @DeleteMapping("/paragraph/{id}")
    public ResponseEntity<Void> getById(@PathVariable Long id) {
        paragraphService.deleteParagraph(id);
        return ResponseEntity.noContent().build();
    }

}
