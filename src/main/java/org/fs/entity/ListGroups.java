package org.fs.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity

@Setter
@Getter
@ToString
public class ListGroups extends Paragraph {

    @OneToMany(mappedBy = "listGroups", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListGroupElement> data;

    public ListGroups() {
        setType(Type.LIST_GROUPS);
    }

}
