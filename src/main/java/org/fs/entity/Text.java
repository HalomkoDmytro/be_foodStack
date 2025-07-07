package org.fs.entity;

import jakarta.persistence.Column;
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
    @Column(columnDefinition = "TEXT")
    private String data;

    public Text() {
        setType(Type.TEXT);
    }

}
