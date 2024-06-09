package com.malimaquintino.javamongo.controllers.v1;

import com.malimaquintino.javamongo.dto.database.DatabaseInputDTO;
import com.malimaquintino.javamongo.dto.database.DatabaseOutputDTO;
import com.malimaquintino.javamongo.dto.table.TableInputDTO;
import com.malimaquintino.javamongo.services.DatabaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/v1/metadata")
public class DatabaseController {
    @Autowired
    private DatabaseService databaseService;

    @PostMapping(path = "/database", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDatabase(@RequestBody DatabaseInputDTO inputDto) {
        log.info("Create new database");
        DatabaseOutputDTO databaseOutputDTO = databaseService.createDatabase(inputDto);
        return ResponseEntity.status(HttpStatus.OK).body(databaseOutputDTO);
    }

    @PutMapping(path = "/database/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDatabase(@PathVariable String id, @RequestBody DatabaseInputDTO inputDto) {
        log.info("Update database {}", id);
        DatabaseOutputDTO databaseOutputDTO = databaseService.updateDatabase(inputDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(databaseOutputDTO);
    }

    @GetMapping(path = "/database/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findDatabaseById(@PathVariable String id) {
        log.info("Get database by id {}", id);
        DatabaseOutputDTO databaseOutputDTO = databaseService.findDatabaseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(databaseOutputDTO);
    }

    @PostMapping(path = "/table", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTable(@RequestBody TableInputDTO inputDto) {
        log.info("Create new table");
        DatabaseOutputDTO databaseOutputDTO = databaseService.createTable(inputDto);
        return ResponseEntity.status(HttpStatus.OK).body(databaseOutputDTO);
    }
}
