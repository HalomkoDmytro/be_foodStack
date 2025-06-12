package org.fs.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Picture extends Paragraph {

    private String data;

    public Type getType() {
        return Type.PICTURE;
    }

}
