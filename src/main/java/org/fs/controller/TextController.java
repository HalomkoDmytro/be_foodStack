package org.fs.controller;

import lombok.RequiredArgsConstructor;
import org.fs.entity.Text;
import org.fs.service.TextParagraphService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TextController {

    private final TextParagraphService textService;

    @GetMapping("/text/{id}")
    public Text getText(@PathVariable Long id) {
        return textService.getText(id);
    }

    @PostMapping("/text")
    public Text updateText(@PathVariable Text text) {
        return textService.updateText(text);
    }

    @DeleteMapping("/text")
    public void deleteText(@PathVariable Long id) {
        textService.deleteText(id);
    }
}
