package com.malimaquintino.javamongo.repositories;

import com.malimaquintino.javamongo.models.Database;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetadataRepository extends MongoRepository<Database, String> {
    Optional<Database> findByQualifiedName(String qualifiedName);

    @Query("""
            {
              $text: {
                $search: ?0
              }
            }
            """)
    List<Database> searchInAllFields(String search);
}
