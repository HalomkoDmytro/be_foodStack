package org.fs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Text extends Paragraph {

    @Lob
    private String data;

    public Text() {
        setType(Type.TEXT);
    }

}
