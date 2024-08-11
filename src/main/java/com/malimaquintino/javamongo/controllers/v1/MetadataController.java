package com.malimaquintino.javamongo.controllers.v1;

import com.malimaquintino.javamongo.dto.DatabaseInputDTO;
import com.malimaquintino.javamongo.models.Database;
import com.malimaquintino.javamongo.services.MetadataService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/v1/metadata")
public class MetadataController {
    @Autowired
    private MetadataService metadataService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDatabase(@RequestBody DatabaseInputDTO inputDto) {
        log.info("Create new database");
        Database database = metadataService.create(inputDto);
        return ResponseEntity.status(HttpStatus.OK).body(database);
    }
}
