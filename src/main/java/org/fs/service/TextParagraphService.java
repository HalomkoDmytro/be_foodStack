package org.fs.service;

import lombok.RequiredArgsConstructor;
import org.fs.entity.Text;
import org.fs.excepiton.EntityNotFoundException;
import org.fs.repository.TextRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TextParagraphService {

    private final TextRepository textRepository;

    public Text getText(Long id) {
        return textRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Text by id [" + id + "] not found."));
    }

    public Text updateText(Text newText) {
        Text oldText;
        if (newText.getId() != null) {
            oldText = getText(newText.getId());
        } else {
            oldText = new Text();
            oldText.setId(newText.getId());
            oldText.setArticle(newText.getArticle());
        }

        oldText.setData(newText.getData());
        oldText.setOrderPosition(newText.getOrderPosition());

        return textRepository.save(oldText);
    }

    public void deleteText(Long id) {
        textRepository.deleteById(id);
    }

}
