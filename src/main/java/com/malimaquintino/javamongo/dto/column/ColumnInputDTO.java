package com.malimaquintino.javamongo.dto.column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnInputDTO {
    private String name;
    private String comment;
    private String status;
}
