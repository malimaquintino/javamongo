package com.malimaquintino.javamongo.repositories;

import com.malimaquintino.javamongo.models.Metadata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetadataRepository extends MongoRepository<Metadata, String> {
    Optional<Metadata> findByQualifiedName(String qualifiedName);

    @Query("""
            { $and: [ { $text: { $search: ?0, $caseSensitive: false } }, { type: { $in: ?1 } } ] }
            """)
    Page<Metadata> searchInAllFields(String search, List<String> type, Pageable pageable);
}
