package com.malimaquintino.javamongo.dto.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseOutputDTO {
    private String id;
    private String qualifiedName;
    private String name;
    private String intTechnology;
    private String databaseId;
    private String containerId;
    private String hostName;
    private String status;
    private String environment;
}
