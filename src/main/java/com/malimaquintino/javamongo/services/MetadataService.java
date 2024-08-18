package com.malimaquintino.javamongo.services;

import com.malimaquintino.javamongo.dto.*;
import com.malimaquintino.javamongo.exception.ResourceNotFoundException;
import com.malimaquintino.javamongo.models.Database;
import com.malimaquintino.javamongo.repositories.MetadataRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
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
            long databases = 0;
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
            // 1. $project: { tablesCount: { $size: "$tables" } }
            AggregationOperation projectStage = project()
                    .andExpression("$size: '$tables'").as("tablesCount");

            // 2. $group: { _id: null, totalTables: { $sum: "$tablesCount" } }
//            AggregationOperation groupStage = group(Fields.fields())
//                    .sum("tablesCount").as("totalTables");

            Aggregation aggregation = newAggregation(
                    project("$size:", "'$tables'"),
                    group(Fields.fields()).count().as("totalTables")
            );

            // Executa a agregação
//            Aggregation aggregation = newAggregation(projectStage, groupStage);
            AggregationResults<TotalTablesDTO> result = mongoTemplate.aggregate(aggregation, "metadata", TotalTablesDTO.class);
            TotalTablesDTO totalTables = result.getUniqueMappedResult();

            return totalTables != null ? totalTables.getTotalTables() : 0;
        } catch (Exception e) {
            log.error("error on get columns total msg={} cause={}", e.getMessage(), e.getCause());
            throw e;
        }
    }
}
