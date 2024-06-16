package com.malimaquintino.javamongo.services;

import com.malimaquintino.javamongo.dto.database.DatabaseOutputDTO;
import com.malimaquintino.javamongo.dto.table.TableInputDTO;
import com.malimaquintino.javamongo.models.Table;
import com.malimaquintino.javamongo.repositories.TableRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class TableService {
    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private TableRepository tableRepository;

    public DatabaseOutputDTO createTable(TableInputDTO tableInputDTO) {
        try {
            Table newTable = tableRepository.save(Table.parseFromDto(tableInputDTO, null));
            return databaseService.updateDatabaseByQualifiedName(tableInputDTO.getDatabaseQualifiedName(), newTable);
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

            Table updatedTable = tableRepository.save(Table.parseFromDto(tableInputDTO, id));
            return databaseService.updateDatabaseByQualifiedName(tableInputDTO.getDatabaseQualifiedName(), updatedTable);
        } catch (Exception e) {
            log.error("error on updateDatabase msg={} cause{}", e.getMessage(), e.getCause());
            throw e;
        }
    }
}
