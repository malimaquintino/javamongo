package com.malimaquintino.javamongo.repositories;

import com.malimaquintino.javamongo.models.Database;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatabaseRepository extends MongoRepository<Database, String> {
}
