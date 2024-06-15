package com.malimaquintino.javamongo.services;

import com.malimaquintino.javamongo.dto.database.DatabaseOutputDTO;
import com.malimaquintino.javamongo.dto.table.TableInputDTO;
import com.malimaquintino.javamongo.models.Database;
import com.malimaquintino.javamongo.models.Table;
import com.malimaquintino.javamongo.repositories.DatabaseRepository;
import com.malimaquintino.javamongo.repositories.TableRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class TableService {
    @Autowired
    private DatabaseRepository databaseRepository;

    @Autowired
    private TableRepository tableRepository;

    public DatabaseOutputDTO createTable(TableInputDTO tableInputDTO) {
        try {
            Optional<Database> optionalDatabase = databaseRepository.findByQualifiedName(tableInputDTO.getDatabaseQualifiedName());
            if (optionalDatabase.isEmpty()) {
                throw new RuntimeException("Database not found");
            }

            Table newTable = tableRepository.save(Table.parseFromDto(tableInputDTO, null));

            Database foundDatabase = optionalDatabase.get();
            foundDatabase.getTables().add(newTable);

            Database updatedDatabase = databaseRepository.save(foundDatabase);
            return Database.parseToDTO(updatedDatabase);
        } catch (Exception e) {
            log.error("error on createTable msg={} cause{}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public DatabaseOutputDTO updateTable(TableInputDTO tableInputDTO, String id) {
        try {

            boolean foundTable = tableRepository.findById(id).isPresent();
            if (!foundTable) {
                throw new RuntimeException("Not found");
            }

            Optional<Database> optionalDatabase = databaseRepository.findByQualifiedName(tableInputDTO.getDatabaseQualifiedName());
            if (optionalDatabase.isEmpty()) {
                throw new RuntimeException("Database not found");
            }

            Table updatedTable = tableRepository.save(Table.parseFromDto(tableInputDTO, id));

            Database database = optionalDatabase.get();
            database.getTables().add(updatedTable);
            Database updatedDatabase = databaseRepository.save(database);
            return Database.parseToDTO(updatedDatabase);
        } catch (Exception e) {
            log.error("error on updateDatabase msg={} cause{}", e.getMessage(), e.getCause());
            throw e;
        }
    }
}
