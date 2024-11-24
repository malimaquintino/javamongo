package com.malimaquintino.javamongo.models;

import com.malimaquintino.javamongo.dto.ColumnInputDTO;
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

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "metadata")
public class Metadata {
    @Id
    private String id;
    private String type;
    private String qualifiedName;
    private Status status;
    private Environment environment;
    private String comment;

    private String databaseName;
    private String indTechnology;
    private String databaseId;
    private String containerId;
    private String hostName;

    private String databaseQualifiedName;
    private String schema;
    private String tableName;

    private String tableQualifiedName;
    private String columnName;


    public static List<Metadata> parseFromDto(DatabaseInputDTO databaseInputDTO) {
        String databaseQualifiedName = generateDatabaseQualifiedName(databaseInputDTO.getEnvironment(), databaseInputDTO.getName());
        List<Metadata> metadatas = new ArrayList<>();

        metadatas.add(Metadata.builder()
                .qualifiedName(databaseQualifiedName)
                .databaseName(databaseInputDTO.getName())
                .indTechnology(databaseInputDTO.getIndTechnology())
                .databaseId(databaseInputDTO.getDatabaseId())
                .containerId(databaseInputDTO.getContainerId())
                .hostName(databaseInputDTO.getHostName())
                .status(Status.valueOf(databaseInputDTO.getStatus()))
                .environment(Environment.valueOf(databaseInputDTO.getEnvironment()))
                .type("DATABASE")
                .build());

        for (TableInputDTO table : databaseInputDTO.getTables()) {
            String tableQualifiedName = generateTableQualifiedName(databaseQualifiedName, table.getSchema(), table.getName());
            metadatas.add(Metadata.builder()
                    .type("TABLE")
                    .qualifiedName(tableQualifiedName)
                    .databaseQualifiedName(databaseQualifiedName)
                    .databaseName(databaseInputDTO.getName())
                    .indTechnology(databaseInputDTO.getIndTechnology())
                    .databaseId(databaseInputDTO.getDatabaseId())
                    .containerId(databaseInputDTO.getContainerId())
                    .hostName(databaseInputDTO.getHostName())
                    .status(Status.valueOf(databaseInputDTO.getStatus()))
                    .environment(Environment.valueOf(databaseInputDTO.getEnvironment()))
                    .schema(table.getSchema())
                    .tableName(table.getName())
                    .comment(table.getComment())
                    .build()
            );

            for (ColumnInputDTO column : table.getColumns()) {
                String columnQualifiedName = generateColumnQualifiedName(tableQualifiedName, column.getName());
                metadatas.add(Metadata.builder()
                        .type("COLUMN")
                        .qualifiedName(columnQualifiedName)
                        .tableQualifiedName(tableQualifiedName)
                        .databaseQualifiedName(databaseQualifiedName)
                        .databaseName(databaseInputDTO.getName())
                        .indTechnology(databaseInputDTO.getIndTechnology())
                        .databaseId(databaseInputDTO.getDatabaseId())
                        .containerId(databaseInputDTO.getContainerId())
                        .hostName(databaseInputDTO.getHostName())
                        .status(Status.valueOf(databaseInputDTO.getStatus()))
                        .environment(Environment.valueOf(databaseInputDTO.getEnvironment()))
                        .schema(table.getSchema())
                        .tableName(table.getName())
                        .columnName(column.getName())
                        .comment(column.getComment())
                        .build()
                );
            }

        }

        databaseInputDTO.getTables().forEach(table -> {
            String tableQualifiedName = generateTableQualifiedName(databaseQualifiedName, table.getSchema(), table.getName());
            metadatas.add(
                    Metadata.builder()
                            .type("TABLE")
                            .qualifiedName(tableQualifiedName)
                            .databaseQualifiedName(databaseQualifiedName)
                            .databaseName(databaseInputDTO.getName())
                            .indTechnology(databaseInputDTO.getIndTechnology())
                            .databaseId(databaseInputDTO.getDatabaseId())
                            .containerId(databaseInputDTO.getContainerId())
                            .hostName(databaseInputDTO.getHostName())
                            .status(Status.valueOf(databaseInputDTO.getStatus()))
                            .environment(Environment.valueOf(databaseInputDTO.getEnvironment()))
                            .build()
            );
        });
        return metadatas;
    }

    private static String generateDatabaseQualifiedName(String environment, String name) {
        return environment.toUpperCase() + "-" + name.toUpperCase();
    }

    private static String generateTableQualifiedName(String databaseQualifiedName, String schema, String name) {
        return databaseQualifiedName + "-" + schema.toUpperCase() + "-" + name.toUpperCase();
    }

    public static String generateColumnQualifiedName(String tableQualifiedName, String name) {
        return tableQualifiedName + "-" + name.toUpperCase();
    }
}
