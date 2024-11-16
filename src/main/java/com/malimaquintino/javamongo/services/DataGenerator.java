package com.malimaquintino.javamongo.services;

import com.malimaquintino.javamongo.dto.DatabaseInputDTO;
import com.malimaquintino.javamongo.util.MockDataGen;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

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

                log.info("generating data...");
            List<DatabaseInputDTO> databases = MockDataGen.generateDatabase();
                log.info("data generated");
                metadataService.createBatch(databases);
            log.info("END");
        }
    }

}
