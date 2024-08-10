package com.malimaquintino.javamongo.services;

import com.malimaquintino.javamongo.dto.column.ColumnInputDTO;
import com.malimaquintino.javamongo.dto.column.ColumnOutputDTO;
import com.malimaquintino.javamongo.exception.ResourceNotFoundException;
import com.malimaquintino.javamongo.models.Column;
import com.malimaquintino.javamongo.models.Table;
import com.malimaquintino.javamongo.repositories.ColumnRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Log4j2
public class ColumnService {
    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private TableService tableService;

    public ColumnOutputDTO createColumn(ColumnInputDTO columnInputDTO) {
        try {
            Table table = tableService.findTableByQualifiedname(columnInputDTO.getTableQualifiedname());
            if (Objects.isNull(table)) {
                throw new ResourceNotFoundException("Table not found");
            }
            String columQualifiedname = generateQualifiedname(table, columnInputDTO.getName());
            Column newColumn = columnRepository.save(Column.parseFromDto(columnInputDTO, table, columQualifiedname, null));
            tableService.updateTable(columnInputDTO.getDatabaseQualifiedname(), table, newColumn);
            return Column.parseToDTO(newColumn);
        } catch (Exception e) {
            log.error("error on createColumn msg={} cause{}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public ColumnOutputDTO updateColumn(String id, ColumnInputDTO columnInputDTO) {
        try {
            boolean foundColumn = columnRepository.findById(id).isPresent();
            if (!foundColumn) {
                throw new ResourceNotFoundException("Column not found");
            }

            Table table = tableService.findTableByQualifiedname(columnInputDTO.getTableQualifiedname());
            if (Objects.isNull(table)) {
                throw new ResourceNotFoundException("Table not found");
            }
            String qualifiedname = generateQualifiedname(table, columnInputDTO.getName());
            Column newColumn = columnRepository.save(Column.parseFromDto(columnInputDTO, table, qualifiedname, id));
            tableService.updateTable(columnInputDTO.getDatabaseQualifiedname(), table, newColumn);
            return Column.parseToDTO(newColumn);
        } catch (Exception e) {
            log.error("error on updateColumn msg={} cause{}", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public String generateQualifiedname(Table table, String name) {
        return table.getQualifiedName() + "-" + name.toUpperCase();
    }
}
