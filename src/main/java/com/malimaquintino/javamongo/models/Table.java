package com.malimaquintino.javamongo.models;


import com.malimaquintino.javamongo.dto.ColumnInputDTO;
import com.malimaquintino.javamongo.dto.TableInputDTO;
import com.malimaquintino.javamongo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Table {
    private String qualifiedName;
    private String schema;
    private String name;
    private String comment;
    private Status status;
    private Set<Column> columns = new HashSet<>();

    public static Table parseFromDto(TableInputDTO tableInputDTO, String databaseQualifiedName) {
        String tableQualifiedname = generateQualifiedname(databaseQualifiedName, tableInputDTO.getSchema(), tableInputDTO.getName());
        return Table.builder()
                .qualifiedName(tableQualifiedname)
                .schema(tableInputDTO.getSchema())
                .name(tableInputDTO.getName())
                .comment(tableInputDTO.getComment())
                .status(Status.valueOf(tableInputDTO.getStatus()))
                .columns(parseColumnsFromDto(tableInputDTO.getColumns(), tableQualifiedname))
                .build();
    }

    private static Set<Column> parseColumnsFromDto(List<ColumnInputDTO> columnInputDTOList, String tableQualifiedname) {
        Set<Column> columns = new HashSet<>();
        columnInputDTOList.forEach(columnInputDTO -> columns.add(Column.parseFromDto(columnInputDTO, tableQualifiedname)));
        return columns;
    }

    private static String generateQualifiedname(String databaseQualifiedName, String schema, String name) {
        return databaseQualifiedName + "-" + schema.toUpperCase() + "-" + name.toUpperCase();
    }
}
