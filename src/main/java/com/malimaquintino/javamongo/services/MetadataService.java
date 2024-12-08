package com.malimaquintino.javamongo.services;

import com.malimaquintino.javamongo.dto.*;
import com.malimaquintino.javamongo.exception.ResourceNotFoundException;
import com.malimaquintino.javamongo.models.Metadata;
import com.malimaquintino.javamongo.repositories.MetadataRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class MetadataService {
    @Autowired
    private MetadataRepository metadataRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void create(DatabaseInputDTO inputDTO) {
        try {
            List<Metadata> metadata = Metadata.parseFromDto(inputDTO);
            metadataRepository.saveAll(metadata);
        } catch (Exception e) {
            log.error("error on create msg={} cause={}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public void createBatch(List<DatabaseInputDTO> databases) {
        try {
            List<Metadata> saveDatabases = new ArrayList<>();
            databases.forEach(database -> saveDatabases.addAll(Metadata.parseFromDto(database)));
            metadataRepository.saveAll(saveDatabases);
        } catch (Exception e) {
            log.error("error on createBatch msg={} cause={}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public Page<Metadata> search(SearchInputDTO searchInputDTO, Pageable pageable) {
        try {
            Page<Metadata> metadata = metadataRepository
                    .searchInAllFields(searchInputDTO.getSearch(), getType(searchInputDTO.getType()), pageable);
            if (metadata.isEmpty()) {
                throw new ResourceNotFoundException("Nothing found!");
            }
            return metadata;
        } catch (Exception e) {
            log.error("error on search msg={} cause={}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    private List<String> getType(String inputType) {
        return switch (inputType.toLowerCase()) {
            case "column" -> List.of("COLUMN");
            case "table" -> List.of("TABLE");
            case "database" -> List.of("DATABASE");
            default -> List.of("COLUMN", "TABLE", "DATABASE");
        };

    }

    public long getTotal() {
        try {
            return mongoTemplate.count(new Query(), Metadata.class);
        } catch (Exception e) {
            log.error("error on get databases total msg={} cause={}", e.getMessage(), e.getCause());
            throw e;
        }
    }
}
