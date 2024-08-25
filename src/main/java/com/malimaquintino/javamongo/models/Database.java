package com.malimaquintino.javamongo.models;

import com.malimaquintino.javamongo.dto.DatabaseInputDTO;
import com.malimaquintino.javamongo.dto.TableInputDTO;
import com.malimaquintino.javamongo.enums.Environment;
import com.malimaquintino.javamongo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "metadata")
public class Database {
    @Id
    private String id;
    private String type;
    private String qualifiedName;
    private String name;
    private String indTechnology;
    private String databaseId;
    private String containerId;
    private String hostName;
    private Status status;
    private Environment environment;
    private Set<Table> tables = new HashSet<>();

    public static Database parseFromDto(DatabaseInputDTO databaseInputDTO) {
        String qualifiedName = generateQualifiedName(databaseInputDTO.getEnvironment(), databaseInputDTO.getName());
        return Database.builder()
                .qualifiedName(qualifiedName)
                .name(databaseInputDTO.getName())
                .indTechnology(databaseInputDTO.getIndTechnology())
                .databaseId(databaseInputDTO.getDatabaseId())
                .containerId(databaseInputDTO.getContainerId())
                .hostName(databaseInputDTO.getHostName())
                .status(Status.valueOf(databaseInputDTO.getStatus()))
                .environment(Environment.valueOf(databaseInputDTO.getEnvironment()))
                .tables(parseTablesFromDto(databaseInputDTO.getTables(), qualifiedName))
                .type("DATABASE")
                .build();
    }

    private static Set<Table> parseTablesFromDto(List<TableInputDTO> tableInputDTOS, String databaseQualifiedName) {
        Set<Table> tables = new HashSet<>();
        tableInputDTOS.forEach(tableInputDTO -> tables.add(Table.parseFromDto(tableInputDTO, databaseQualifiedName)));
        return tables;
    }

    private static String generateQualifiedName(String environment, String name) {
        return environment.toUpperCase() + "-" + name.toUpperCase();
    }
}
