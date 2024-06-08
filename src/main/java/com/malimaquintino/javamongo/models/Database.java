package com.malimaquintino.javamongo.models;

import com.malimaquintino.javamongo.dto.database.DatabaseInputDTO;
import com.malimaquintino.javamongo.dto.database.DatabaseOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Database {
    @Id
    private String id;
    private String qualifiedName;
    private String namDatabase;
    private String indTechnology;
    private String databaseId;
    private String containerId;
    private String hostName;

    public static Database parseFromDto(DatabaseInputDTO databaseInputDTO, String id) {
        return Database.builder()
                .id(id)
                .qualifiedName(databaseInputDTO.getQualifiedName())
                .namDatabase(databaseInputDTO.getNamDatabase())
                .indTechnology(databaseInputDTO.getIndTechnology())
                .databaseId(databaseInputDTO.getDatabaseId())
                .containerId(databaseInputDTO.getContainerId())
                .hostName(databaseInputDTO.getHostName())
                .build();
    }

    public static DatabaseOutputDTO parseToDTO(Database database) {
        return DatabaseOutputDTO.builder()
                .id(database.getId())
                .qualifiedName(database.getQualifiedName())
                .namDatabase(database.getNamDatabase())
                .intTechnology(database.getIndTechnology())
                .databaseId(database.getDatabaseId())
                .containerId(database.getContainerId())
                .hostName(database.getHostName())
                .build();
    }
}
