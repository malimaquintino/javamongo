package com.malimaquintino.javamongo.models;

import com.malimaquintino.javamongo.dto.database.DatabaseInputDTO;
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
    private String intTechnology;
    private String databaseId;
    private String containerId;

    public static Database parseFromDto(DatabaseInputDTO databaseInputDTO, String id) {
        return Database.builder()
                .id(id)
                .qualifiedName(databaseInputDTO.getQualifiedName())
                .namDatabase(databaseInputDTO.getNamDatabase())
                .intTechnology(databaseInputDTO.getIntTechnology())
                .databaseId(databaseInputDTO.getDatabaseId())
                .containerId(databaseInputDTO.getContainerId())
                .build();
    }
}
