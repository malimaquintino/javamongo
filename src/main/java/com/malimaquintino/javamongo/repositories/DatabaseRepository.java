package com.malimaquintino.javamongo.repositories;

import com.malimaquintino.javamongo.models.Database;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DatabaseRepository extends MongoRepository<Database, String> {
    Optional<Database> findByQualifiedName(String qualifiedName);
}
