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
public class Picture extends Paragraph {

    @Lob
    private String data;

    public Picture() {
        setType(Type.PICTURE);
    }
}
