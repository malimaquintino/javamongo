package com.malimaquintino.javamongo.services;

import com.malimaquintino.javamongo.dto.table.TableInputDTO;
import com.malimaquintino.javamongo.dto.table.TableOutputDTO;
import com.malimaquintino.javamongo.models.Database;
import com.malimaquintino.javamongo.models.Table;
import com.malimaquintino.javamongo.repositories.TableRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class TableService {
    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private TableRepository tableRepository;

    public TableOutputDTO createTable(TableInputDTO tableInputDTO) {
        try {
            Database database = databaseService.findDatabaseByQualifiedname(tableInputDTO.getDatabaseQualifiedName());
            String qualifiedName = generateQualifiedname(database, tableInputDTO.getSchema(), tableInputDTO.getName());
            Table newTable = tableRepository.save(Table.parseFromDto(tableInputDTO, qualifiedName, null));
            databaseService.updateDatabase(database, newTable);
            return Table.parseToDTO(newTable);
        } catch (Exception e) {
            log.error("error on createTable msg={} cause{}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public TableOutputDTO updateTable(TableInputDTO tableInputDTO, String id) {
        try {
            boolean foundTable = tableRepository.findById(id).isPresent();
            if (!foundTable) {
                throw new RuntimeException("Not found");
            }

            Database database = databaseService.findDatabaseByQualifiedname(tableInputDTO.getDatabaseQualifiedName());
            String qualifiedName = generateQualifiedname(database, tableInputDTO.getSchema(), tableInputDTO.getName());
            Table updatedTable = tableRepository.save(Table.parseFromDto(tableInputDTO, qualifiedName, id));
            databaseService.updateDatabase(database, updatedTable);
            return Table.parseToDTO(updatedTable);
        } catch (Exception e) {
            log.error("error on updateDatabase msg={} cause{}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public Table findTableByQualifiedname(String qualifiedName) {
        try {
            Optional<Table> optionalTable = tableRepository.findByQualifiedName(qualifiedName);
            if (optionalTable.isEmpty()) {
                throw new RuntimeException("Table not found");
            }
            return optionalTable.get();
        } catch (Exception e) {
            log.error("error on findTableByQualifiedname msg={} cause{}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public String generateQualifiedname(Database database, String schema, String name) {
        return database.getQualifiedName() + "-" + schema.toUpperCase() + "-" + name.toUpperCase();
    }
}
