package com.malimaquintino.javamongo.dto.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseInputDTO {
    private String qualifiedName;
    private String namDatabase;
    private String indTechnology;
    private String databaseId;
    private String containerId;
    private String hostName;
}
