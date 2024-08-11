package com.malimaquintino.javamongo.services;

import com.malimaquintino.javamongo.repositories.MetadataRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@Log4j2

public class DataGenerator {

    @Autowired
    private MetadataRepository metadataRepository;

    @Value("${application.generateData}")
    private int flgDataGen;

    @Bean
    public void generateData() {
        if (flgDataGen == 1) {
            log.info("generating data...");
        }
    }
}
