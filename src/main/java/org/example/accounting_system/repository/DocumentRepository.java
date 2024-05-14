package org.example.accounting_system.repository;

import org.example.accounting_system.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findDocumentByDocNumber(long docNumber);

    @Query(nativeQuery = true, value = """
            INSERT INTO document (doc_number, date, sum, description)
            VALUES (?1, ?2, 0.0, ?3) returning *
            """)
    Document create(long docNumber, LocalDate date, String description);


}
