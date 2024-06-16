package com.malimaquintino.javamongo.services;

import com.malimaquintino.javamongo.dto.database.DatabaseInputDTO;
import com.malimaquintino.javamongo.dto.database.DatabaseOutputDTO;
import com.malimaquintino.javamongo.models.Database;
import com.malimaquintino.javamongo.models.Table;
import com.malimaquintino.javamongo.repositories.DatabaseRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class DatabaseService {

    @Autowired
    private DatabaseRepository databaseRepository;

    public DatabaseOutputDTO createDatabase(DatabaseInputDTO databaseInputDTO) {
        try {
            Database database = Database.parseFromDto(databaseInputDTO, null);
            database.setQualifiedName(generateQualifiedName(database));
            Database newDatabase = databaseRepository.save(database);
            return Database.parseToDTO(newDatabase);
        } catch (Exception e) {
            log.error("error on createDatabase msg={} cause{}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public DatabaseOutputDTO updateDatabase(DatabaseInputDTO databaseInputDTO, String id) {
        try {

            boolean foundDatabase = databaseRepository.findById(id).isPresent();
            if (!foundDatabase) {
                throw new RuntimeException("Not found");
            }
            Database database = Database.parseFromDto(databaseInputDTO, id);
            database.setQualifiedName(generateQualifiedName(database));
            Database updatedDatabase = databaseRepository.save(database);
            return Database.parseToDTO(updatedDatabase);
        } catch (Exception e) {
            log.error("error on updateDatabase msg={} cause{}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public DatabaseOutputDTO updateDatabaseByQualifiedName(String qualifiedName, Table newTable) {
        try {
            Optional<Database> optionalDatabase = databaseRepository.findByQualifiedName(qualifiedName);
            if (optionalDatabase.isEmpty()) {
                throw new RuntimeException("Database not found");
            }
            Database database = optionalDatabase.get();
            database.getTables().add(newTable);
            Database updatedDatabase = databaseRepository.save(database);
            return Database.parseToDTO(updatedDatabase);
        } catch (Exception e) {
            log.error("error on updateDatabaseByQualifiedName msg={} cause{}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public DatabaseOutputDTO findDatabaseById(String id) {
        try {
            Database foundDatabase = databaseRepository.findById(id).orElse(null);
            if (Objects.isNull(foundDatabase)) {
                throw new RuntimeException("Not found");
            }
            return Database.parseToDTO(foundDatabase);
        } catch (Exception e) {
            log.error("error on findDatabaseById msg={} cause{}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public String generateQualifiedName(Database database) {
        return String.format(database.getEnvironment().toString().toUpperCase(), "-", database.getName().toUpperCase());
    }
}
