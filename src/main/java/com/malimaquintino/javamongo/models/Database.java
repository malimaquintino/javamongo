package com.malimaquintino.javamongo.models;

import com.malimaquintino.javamongo.dto.database.DatabaseInputDTO;
import com.malimaquintino.javamongo.dto.database.DatabaseOutputDTO;
import com.malimaquintino.javamongo.enums.Environment;
import com.malimaquintino.javamongo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Database {
    @Id
    private String id;
    private String qualifiedName;
    private String name;
    private String indTechnology;
    private String databaseId;
    private String containerId;
    private String hostName;
    private Status status;
    private Environment environment;
    private Set<Table> tables;

    public static Database parseFromDto(DatabaseInputDTO databaseInputDTO, String id) {
        return Database.builder()
                .id(id)
                .qualifiedName(databaseInputDTO.getQualifiedName())
                .name(databaseInputDTO.getName())
                .indTechnology(databaseInputDTO.getIndTechnology())
                .databaseId(databaseInputDTO.getDatabaseId())
                .containerId(databaseInputDTO.getContainerId())
                .hostName(databaseInputDTO.getHostName())
                .status(Status.valueOf(databaseInputDTO.getStatus()))
                .environment(Environment.valueOf(databaseInputDTO.getEnvironment()))
                .build();
    }

    public static DatabaseOutputDTO parseToDTO(Database database) {
        return DatabaseOutputDTO.builder()
                .id(database.getId())
                .qualifiedName(database.getQualifiedName())
                .name(database.getName())
                .intTechnology(database.getIndTechnology())
                .databaseId(database.getDatabaseId())
                .containerId(database.getContainerId())
                .hostName(database.getHostName())
                .status(database.getStatus().toString())
                .environment(database.getEnvironment().toString())
                .build();
    }
}
