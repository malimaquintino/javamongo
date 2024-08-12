package com.malimaquintino.javamongo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableInputDTO {
    private String schema;
    private String name;
    private String comment;
    private String status;
    private List<ColumnInputDTO> columns;
}
