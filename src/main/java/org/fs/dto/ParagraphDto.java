package org.fs.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import org.fs.entity.Type;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextDto.class, name = "TEXT"),
        @JsonSubTypes.Type(value = PictureDto.class, name = "PICTURE"),
        @JsonSubTypes.Type(value = ListGroupsDto.class, name = "LIST_GROUPS")
})
@Getter
@Setter
public abstract class ParagraphDto {

    private Long id;

    private int orderPosition;

    private Type type;

}
