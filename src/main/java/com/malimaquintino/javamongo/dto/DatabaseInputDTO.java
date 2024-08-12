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
public class DatabaseInputDTO {
    private String name;
    private String indTechnology;
    private String databaseId;
    private String containerId;
    private String hostName;
    private String status;
    private String environment;
    private List<TableInputDTO> tables;
}
