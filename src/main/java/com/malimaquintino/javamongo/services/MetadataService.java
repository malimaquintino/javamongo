package com.malimaquintino.javamongo.services;

import com.malimaquintino.javamongo.dto.*;
import com.malimaquintino.javamongo.exception.ResourceNotFoundException;
import com.malimaquintino.javamongo.models.Database;
import com.malimaquintino.javamongo.repositories.MetadataRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
@Log4j2
public class MetadataService {
    @Autowired
    private MetadataRepository metadataRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Database create(DatabaseInputDTO inputDTO) {
        try {
            Database database = Database.parseFromDto(inputDTO);
            Database newDatabase = metadataRepository.save(database);
            return newDatabase;
        } catch (Exception e) {
            log.error("error on create msg={} cause={}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public List<Database> search(SearchInputDTO searchInputDTO) {
        try {
            List<Database> databases = metadataRepository.searchInAllFields(searchInputDTO.getSearch());
            if (databases.isEmpty()) {
                throw new ResourceNotFoundException("Nothing found!");
            }
            return databases;
        } catch (Exception e) {
            log.error("error on search msg={} cause={}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public TotalOutputDTO getTotals() {
        try {
            long databases = getTotalDatabases();
            long tables = getTotalTables();
            long columns = getTotalColumns();
            return new TotalOutputDTO(databases, tables, columns);
        } catch (Exception e) {
            log.error("error on get totals msg={} cause={}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    /**
     * query
     * [
     * {
     * $unwind: "$tables"
     * },
     * {
     * $unwind: "$tables.columns"
     * },
     * {
     * $group: {
     * _id: null,
     * totalColumns: { $sum: 1 }
     * }
     * }
     * ]
     */
    private long getTotalColumns() {
        try {
            Aggregation aggregation = newAggregation(
                    unwind("tables"),
                    unwind("tables.columns"),
                    group(Fields.fields()).count().as("totalColumns")
            );

            AggregationResults<TotalColumnsDTO> result = mongoTemplate.aggregate(aggregation, "metadata", TotalColumnsDTO.class);
            TotalColumnsDTO total = result.getUniqueMappedResult();
            return total != null ? total.getTotalColumns() : 0;
        } catch (Exception e) {
            log.error("error on get columns total msg={} cause={}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    /**
     * [
     * {
     * $project: {
     * tablesCount: { $size: "$tables" }
     * }
     * },
     * {
     * $group: {
     * _id: null,
     * totalTables: { $sum: "$tablesCount" }
     * }
     * }
     * ]
     */
    private long getTotalTables() {
        try {

            Aggregation aggregation = newAggregation(
                    project()
                            .andExpression("{$size: '$tables'}").as("tablesCount"),
                    group(Fields.fields())
                            .sum("tablesCount").as("totalTables")
            );

            // Executando a agregação
            AggregationResults<TotalTablesDTO> result = mongoTemplate.aggregate(aggregation, "metadata", TotalTablesDTO.class);
            TotalTablesDTO total = result.getUniqueMappedResult();

            return total != null ? total.getTotalTables() : 0;
        } catch (Exception e) {
            log.error("error on get columns total msg={} cause={}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    private long getTotalDatabases() {
        try {
            return mongoTemplate.count(new Query(), Database.class);
        } catch (Exception e) {
            log.error("error on get databases total msg={} cause={}", e.getMessage(), e.getCause());
            throw e;
        }
    }
}
