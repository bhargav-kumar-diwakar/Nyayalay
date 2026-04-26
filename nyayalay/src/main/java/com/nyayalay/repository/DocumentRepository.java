package com.nyayalay.repository;

import com.nyayalay.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.JpaParameters;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {
    List<Document> findByCourtCaseId(Long caseId);
    List<Document> findByFileType(String fileType);
}
