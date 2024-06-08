package com.malimaquintino.javamongo.models;

import com.malimaquintino.javamongo.dto.column.ColumnInputDTO;
import com.malimaquintino.javamongo.dto.column.ColumnOutputDTO;
import com.malimaquintino.javamongo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Column {
    @Id
    private String id;
    private String qualifiedName;
    private String name;
    private String comment;
    private Status status;
    private Table table;

    public static Column parseFromDto(ColumnInputDTO columnInputDTO) {
        return Column.builder()
                .name(columnInputDTO.getName())
                .comment(columnInputDTO.getComment())
                .status(Status.valueOf(columnInputDTO.getStatus()))
                .build();
    }

    public static ColumnOutputDTO parseToDTO(Column column) {
        return ColumnOutputDTO.builder()
                .qualifiedName(column.getQualifiedName())
                .name(column.getName())
                .comment(column.getComment())
                .status(column.getStatus().toString())
                .build();
    }
}
