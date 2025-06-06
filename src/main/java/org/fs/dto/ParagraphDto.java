package org.fs.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
        @JsonSubTypes.Type(value = PictureDto.class, name = "PICTURE")
})
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class ParagraphDto {

    private Long id;

    private int orderPosition;

    public abstract Type getType();
}
