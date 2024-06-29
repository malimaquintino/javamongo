package com.malimaquintino.javamongo.controllers.v1;

import com.malimaquintino.javamongo.dto.table.TableInputDTO;
import com.malimaquintino.javamongo.dto.table.TableOutputDTO;
import com.malimaquintino.javamongo.services.TableService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/v1/table")
public class TableController {
    @Autowired
    private TableService tableService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTable(@RequestBody TableInputDTO inputDto) {
        log.info("Create new table");
        TableOutputDTO tableOutputDTO = tableService.createTable(inputDto);
        return ResponseEntity.status(HttpStatus.OK).body(tableOutputDTO);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTable(@PathVariable String id, @RequestBody TableInputDTO inputDto) {
        log.info("Update database {}", id);
        TableOutputDTO tableOutputDTO = tableService.updateTable(inputDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(tableOutputDTO);
    }
}
