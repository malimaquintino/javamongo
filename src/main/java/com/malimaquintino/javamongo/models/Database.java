package com.malimaquintino.javamongo.models;

import com.malimaquintino.javamongo.dto.database.DatabaseInputDTO;
import com.malimaquintino.javamongo.dto.database.DatabaseOutputDTO;
import com.malimaquintino.javamongo.dto.table.TableOutputDTO;
import com.malimaquintino.javamongo.enums.Environment;
import com.malimaquintino.javamongo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Database {
    @Id
    private String id;
    @Indexed(unique = true)
    private String qualifiedName;
    private String name;
    private String indTechnology;
    private String databaseId;
    private String containerId;
    private String hostName;
    private Status status;
    private Environment environment;
    private Set<Table> tables = new HashSet<>();

    public static Database parseFromDto(DatabaseInputDTO databaseInputDTO, String id) {
        return Database.builder()
                .id(id)
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
        List<TableOutputDTO> tables = Table.parseListToDTO(database.getTables());
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
                .tables(tables)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Database database = (Database) o;
        return Objects.equals(id, database.id) && Objects.equals(qualifiedName, database.qualifiedName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qualifiedName);
    }
}
