package com.malimaquintino.javamongo.services;

import com.malimaquintino.javamongo.dto.database.DatabaseInputDTO;
import com.malimaquintino.javamongo.dto.database.DatabaseOutputDTO;
import com.malimaquintino.javamongo.models.Database;
import com.malimaquintino.javamongo.repositories.DatabaseRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class DatabaseService {

    @Autowired
    private DatabaseRepository databaseRepository;

    public DatabaseOutputDTO createDatabase(DatabaseInputDTO databaseInputDTO) {
        try {
            Database newDatabase = databaseRepository.save(Database.parseFromDto(databaseInputDTO, null));
            return DatabaseOutputDTO.parseToDTO(newDatabase);
        } catch (Exception e) {
            log.error("error on createDatabase msg={} cause{}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public DatabaseOutputDTO updateDatabase(DatabaseInputDTO databaseInputDTO, String id) {
        try {
            Database updatedDatabase = databaseRepository.save(Database.parseFromDto(databaseInputDTO, id));
            return DatabaseOutputDTO.parseToDTO(updatedDatabase);
        } catch (Exception e) {
            log.error("error on updateDatabase msg={} cause{}", e.getMessage(), e.getCause());
            throw e;
        }
    }
}
