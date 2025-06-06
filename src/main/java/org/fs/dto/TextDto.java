package org.fs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.fs.entity.Type;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TextDto extends ParagraphDto{

    private String data;

    public Type getType() {
        return Type.TEXT;
    }
}
