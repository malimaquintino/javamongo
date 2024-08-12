package com.malimaquintino.javamongo.services;

import com.malimaquintino.javamongo.dto.DatabaseInputDTO;
import com.malimaquintino.javamongo.util.MockDataGen;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@Log4j2

public class DataGenerator {

    @Autowired
    private MetadataService metadataService;

    @Value("${application.generateData}")
    private int flgDataGen;

    @Bean
    public void generateData() {
        if (flgDataGen == 1) {
            for (int i = 0; i < 100; i++) {
                log.info("generating data...");
                DatabaseInputDTO databaseInputDTO = MockDataGen.generateDatabase();
                log.info("data generated");
                metadataService.create(databaseInputDTO);
                log.info("Metadata created! database={} numTables={}",
                        databaseInputDTO.getName(),
                        databaseInputDTO.getTables().size()
                );
            }
            log.info("END");
        }
    }

}
