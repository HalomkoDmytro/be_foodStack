package org.fs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.fs.entity.Type;

import java.util.List;

@Getter
@Setter
@ToString
public class ListGroupsDto extends ParagraphDto {

    private List<ListGroupElementDto> data;

    public ListGroupsDto() {
        setType(Type.LIST_GROUPS);
    }

}
