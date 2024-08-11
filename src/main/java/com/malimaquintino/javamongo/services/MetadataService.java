package com.malimaquintino.javamongo.services;

import com.malimaquintino.javamongo.dto.DatabaseInputDTO;
import com.malimaquintino.javamongo.models.Database;
import com.malimaquintino.javamongo.repositories.MetadataRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MetadataService {
    @Autowired
    private MetadataRepository metadataRepository;

    public Database create(DatabaseInputDTO inputDTO) {
        try {
            Database database = Database.parseFromDto(inputDTO);
            Database newDatabase = metadataRepository.save(database);
            return newDatabase;
        } catch (Exception e) {
            log.error("error on create metadata msg={} cause{}", e.getMessage(), e.getCause());
            throw e;
        }
    }
}
