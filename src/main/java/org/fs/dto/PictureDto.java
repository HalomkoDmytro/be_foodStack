package org.fs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.fs.entity.Type;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PictureDto extends ParagraphDto{

    private String data;

    public Type getType() {
        return Type.PICTURE;
    }

}
