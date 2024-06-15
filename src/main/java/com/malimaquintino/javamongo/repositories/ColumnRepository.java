package com.malimaquintino.javamongo.repositories;

import com.malimaquintino.javamongo.models.Column;
import com.malimaquintino.javamongo.models.Table;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ColumnRepository extends MongoRepository<Column, String> {
    Optional<Table> findByQualifiedName(String qualifiedName);
}
