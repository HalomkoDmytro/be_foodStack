package org.fs.converter;

import org.fs.dto.ParagraphDto;
import org.fs.dto.PictureDto;
import org.fs.dto.TextDto;
import org.fs.entity.Paragraph;
import org.fs.entity.Picture;
import org.fs.entity.Text;

public final class ParagraphConverter {

    public static Paragraph convert(ParagraphDto dto) {
        return switch (dto.getType()) {
            case PICTURE -> convertPicture((PictureDto) dto);
            case TEXT -> convertText((TextDto) dto);
            default -> throw new UnsupportedOperationException();
        };
    }

    public static ParagraphDto convert(Paragraph paragraph) {
        return switch (paragraph.getType()) {
            case PICTURE -> convert((Picture) paragraph);
            case TEXT -> convert((Text) paragraph);
            default -> throw new UnsupportedOperationException();
        };
    }

    private static PictureDto convert(Picture picture) {
        PictureDto pictureDto = new PictureDto();
        pictureDto.setId(picture.getId());
        pictureDto.setOrderPosition(picture.getOrderPosition());
        pictureDto.setData(picture.getData());
        return pictureDto;
    }

    private static TextDto convert(Text text) {
        TextDto textDto = new TextDto();
        textDto.setId(text.getId());
        textDto.setOrderPosition(text.getOrderPosition());
        textDto.setData(text.getData());
        return textDto;
    }

    private static Text convertText(TextDto dto) {
        Text text = new Text();
        text.setId(dto.getId());
        text.setArticle(null);
        text.setData(dto.getData());
        return text;
    }

    private static Picture convertPicture(PictureDto dto) {
        Picture picture = new Picture();
        picture.setId(dto.getId());
        picture.setData(dto.getData());
        return picture;
    }

}
