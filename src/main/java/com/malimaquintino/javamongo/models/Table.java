package com.malimaquintino.javamongo.models;

import com.malimaquintino.javamongo.dto.table.TableInputDTO;
import com.malimaquintino.javamongo.dto.table.TableOutputDTO;
import com.malimaquintino.javamongo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Table {
    @Id
    private String id;
    @Indexed(unique = true)
    private String qualifiedName;
    private String schema;
    private String name;
    private String comment;
    private Status status;
    private Database database;
    private Set<Column> columns;

    public static Table parseFromDto(TableInputDTO tableInputDTO, String id) {
        return Table.builder()
                .id(id)
                .schema(tableInputDTO.getSchema())
                .name(tableInputDTO.getName())
                .comment(tableInputDTO.getComment())
                .status(Status.valueOf(tableInputDTO.getStatus()))
                .build();
    }

    public static TableOutputDTO parseToDTO(Table table) {
        return TableOutputDTO.builder()
                .qualifiedName(table.getQualifiedName())
                .schema(table.getSchema())
                .name(table.getName())
                .comment(table.getComment())
                .status(table.getStatus().toString())
                .build();
    }

    public static List<TableOutputDTO> parseListToDTO(Set<Table> tables) {
        List<TableOutputDTO> result = new ArrayList<>();
        if (Objects.nonNull(tables) && !tables.isEmpty()) {
            tables.forEach(table -> result.add(parseToDTO(table)));
        }
        return result;
    }
}
