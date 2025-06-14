package org.fs.converter;

import org.fs.dto.ListGroupElementDto;
import org.fs.dto.ListGroupsDto;
import org.fs.dto.ParagraphDto;
import org.fs.dto.PictureDto;
import org.fs.dto.TextDto;
import org.fs.entity.ListGroupElement;
import org.fs.entity.ListGroups;
import org.fs.entity.Paragraph;
import org.fs.entity.Picture;
import org.fs.entity.Text;

import java.util.stream.Collectors;

public final class ParagraphConverter {

    public static Paragraph convert(ParagraphDto dto) {
        return switch (dto.getType()) {
            case PICTURE -> convertPicture((PictureDto) dto);
            case TEXT -> convertText((TextDto) dto);
            case LIST_GROUPS -> convertListGroups((ListGroupsDto) dto);
            default -> throw new UnsupportedOperationException();
        };
    }

    public static ParagraphDto convert(Paragraph paragraph) {
        return switch (paragraph.getType()) {
            case PICTURE -> convert((Picture) paragraph);
            case TEXT -> convert((Text) paragraph);
            case LIST_GROUPS -> convert((ListGroups) paragraph);
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

    private static ListGroupsDto convert(ListGroups lg) {
        ListGroupsDto lgDto = new ListGroupsDto();
        lgDto.setId(lg.getId());
        lgDto.setData(lg.getData().stream()
                .map(ParagraphConverter::convert)
                .collect(Collectors.toList()));
        return lgDto;
    }

    private static ListGroupElementDto convert(ListGroupElement lge) {
        ListGroupElementDto dto = new ListGroupElementDto();
        dto.setId(lge.getId());
        dto.setText(lge.getText());
        dto.setSize(lge.getSize());
        return dto;
    }

    private static Text convertText(TextDto dto) {
        Text text = new Text();
        text.setId(dto.getId());
        text.setArticle(null);
        text.setData(dto.getData());
        text.setOrderPosition(dto.getOrderPosition());
        return text;
    }

    private static Picture convertPicture(PictureDto dto) {
        Picture picture = new Picture();
        picture.setId(dto.getId());
        picture.setData(dto.getData());
        picture.setOrderPosition(dto.getOrderPosition());
        return picture;
    }

    private static ListGroups convertListGroups(ListGroupsDto dto) {
        ListGroups lg = new ListGroups();
        lg.setId(dto.getId());
        lg.setOrderPosition(dto.getOrderPosition());
        lg.setData(dto.getData()
                .stream()
                .map(ParagraphConverter::convertListGroupElement)
                .collect(Collectors.toList()));
        lg.getData().forEach(d -> d.setListGroups(lg));
        return lg;
    }

    private static ListGroupElement convertListGroupElement(ListGroupElementDto dto) {
        ListGroupElement lge = new ListGroupElement();
        lge.setId(dto.getId());
        lge.setText(dto.getText());
        lge.setSize(dto.getSize());
        return lge;
    }

}
