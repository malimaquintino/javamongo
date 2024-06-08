package com.malimaquintino.javamongo.dto.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableOutputDTO {
    private String id;
    private String qualifiedName;
    private String schema;
    private String name;
    private String comment;
    private String status;
}
