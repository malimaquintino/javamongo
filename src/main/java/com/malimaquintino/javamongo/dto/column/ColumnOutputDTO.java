package com.malimaquintino.javamongo.dto.column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColumnOutputDTO {
    private String id;
    private String qualifiedName;
    private String name;
    private String comment;
    private String status;
}
