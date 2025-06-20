package org.fs.service;

import lombok.RequiredArgsConstructor;
import org.fs.entity.Paragraph;
import org.fs.entity.Picture;
import org.fs.entity.Type;
import org.fs.excepiton.EntityNotFoundException;
import org.fs.repository.ParagraphRepository;
import org.fs.repository.PictureRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParagraphService {

    private final ParagraphRepository paragraphRepository;
    private final PictureRepository pictureRepository;
    private final S3Service s3Service;

    public void deleteParagraph(Long id) {
        Paragraph paragraph = paragraphRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity by id [" + id + "] not found."));

        if (paragraph.getType().equals(Type.PICTURE)) {
            Picture picture = pictureRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Picture by id [" + id + "] not found."));
            s3Service.deleteFile(picture.getData());
        }

        paragraphRepository.deleteById(id);
    }

}
