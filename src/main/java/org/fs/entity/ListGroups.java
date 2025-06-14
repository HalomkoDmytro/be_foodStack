package org.fs.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ListGroups extends Paragraph {

    @OneToMany(mappedBy = "listGroups", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListGroupElement> data;

    public Type getType() {
        return Type.LIST_GROUPS;
    }

}
