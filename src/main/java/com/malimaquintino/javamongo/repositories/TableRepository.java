package com.malimaquintino.javamongo.repositories;

import com.malimaquintino.javamongo.models.Table;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TableRepository extends MongoRepository<Table, String> {
    Optional<Table> findByQualifiedName(String qualifiedName);
}
