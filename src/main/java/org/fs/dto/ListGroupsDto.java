package org.fs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.fs.entity.Type;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ListGroupsDto extends ParagraphDto {

    private List<ListGroupElementDto> data;

    public Type type = Type.LIST_GROUPS;
}
