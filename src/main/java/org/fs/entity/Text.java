package org.fs.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Text extends Paragraph {

    private String data;

    public Type getType() {
        return Type.TEXT;
    }

}
