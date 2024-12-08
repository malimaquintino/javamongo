package com.malimaquintino.javamongo.controllers.v1;

import com.malimaquintino.javamongo.dto.DatabaseInputDTO;
import com.malimaquintino.javamongo.dto.SearchInputDTO;
import com.malimaquintino.javamongo.models.Metadata;
import com.malimaquintino.javamongo.services.MetadataService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Log4j2
@RestController
@RequestMapping("/v1/metadata")
@CrossOrigin(origins = "*")
public class MetadataController {
    @Autowired
    private MetadataService metadataService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDatabase(@RequestBody DatabaseInputDTO inputDto) {
        log.info("Create new database");
        metadataService.create(inputDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> search(@RequestBody SearchInputDTO searchInputDTO,
                                    @PageableDefault(sort = {"name"}, direction = ASC) Pageable pageable) {
        log.info("Search..");
        Page<Metadata> response = metadataService.search(searchInputDTO, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "totals")
    public ResponseEntity<?> getTotals() {
        log.info("get totals..");
        Long total = metadataService.getTotal();
        return ResponseEntity.status(HttpStatus.OK).body(total);
    }

}
