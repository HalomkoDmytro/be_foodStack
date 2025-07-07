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
public class Picture extends Paragraph {

    @Lob
    @Column(columnDefinition = "TEXT")
    private String data;

    public Picture() {
        setType(Type.PICTURE);
    }
}
