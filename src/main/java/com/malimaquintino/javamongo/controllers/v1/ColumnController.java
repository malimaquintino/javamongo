package com.malimaquintino.javamongo.controllers.v1;

import com.malimaquintino.javamongo.dto.column.ColumnInputDTO;
import com.malimaquintino.javamongo.dto.column.ColumnOutputDTO;
import com.malimaquintino.javamongo.services.ColumnService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/v1/column")
public class ColumnController {
    @Autowired
    private ColumnService columnService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createColumn(@RequestBody ColumnInputDTO inputDto) {
        log.info("Create new column");
        ColumnOutputDTO columnOutputDTO = columnService.createColumn(inputDto);
        return ResponseEntity.status(HttpStatus.OK).body(columnOutputDTO);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateColumn(@PathVariable String id, @RequestBody ColumnInputDTO inputDto) {
        log.info("Update column {}", id);
        ColumnOutputDTO columnOutputDTO = columnService.updateColumn(id, inputDto);
        return ResponseEntity.status(HttpStatus.OK).body(columnOutputDTO);
    }
}
