package com.malimaquintino.javamongo.dto.database;

import com.malimaquintino.javamongo.models.Database;
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
    private String namDatabase;
    private String intTechnology;
    private String databaseId;
    private String containerId;

    public static DatabaseOutputDTO parseToDTO(Database database) {
        return DatabaseOutputDTO.builder()
                .id(database.getDatabaseId())
                .qualifiedName(database.getQualifiedName())
                .namDatabase(database.getNamDatabase())
                .intTechnology(database.getIntTechnology())
                .databaseId(database.getDatabaseId())
                .containerId(database.getContainerId())
                .build();
    }
}
