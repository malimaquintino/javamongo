package com.malimaquintino.javamongo.dto.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableInputDTO {
    private String databaseQualifiedName;
    private String schema;
    private String name;
    private String comment;
    private String status;
}
