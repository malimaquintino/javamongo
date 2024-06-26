package com.malimaquintino.javamongo.models;

import com.malimaquintino.javamongo.dto.column.ColumnInputDTO;
import com.malimaquintino.javamongo.dto.column.ColumnOutputDTO;
import com.malimaquintino.javamongo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Column {
    @Id
    private String id;
    @Indexed(unique = true)
    private String qualifiedName;
    private String name;
    private String comment;
    private Status status;
    private Table table;

    public static Column parseFromDto(ColumnInputDTO columnInputDTO, String qualifiedName, String id) {
        return Column.builder()
                .id(id)
                .qualifiedName(qualifiedName)
                .name(columnInputDTO.getName())
                .comment(columnInputDTO.getComment())
                .status(Status.valueOf(columnInputDTO.getStatus()))
                .build();
    }

    public static ColumnOutputDTO parseToDTO(Column column) {
        return ColumnOutputDTO.builder()
                .id(column.getId())
                .qualifiedName(column.getQualifiedName())
                .name(column.getName())
                .comment(column.getComment())
                .status(column.getStatus().toString())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column = (Column) o;
        return Objects.equals(id, column.id) && Objects.equals(qualifiedName, column.qualifiedName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qualifiedName);
    }
}
