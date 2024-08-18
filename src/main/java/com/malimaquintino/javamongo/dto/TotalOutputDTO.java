package com.malimaquintino.javamongo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TotalOutputDTO {
    private long databases;
    private long tables;
    private long columns;
}
