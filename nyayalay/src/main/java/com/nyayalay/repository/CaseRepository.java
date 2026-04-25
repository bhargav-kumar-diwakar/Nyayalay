package com.nyayalay.repository;

import com.nyayalay.entity.CourtCase;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CaseRepository extends JpaRepository<CourtCase,Long> {
    Optional<CourtCase> findByCaseNumber(String caseNumber);
    List<CourtCase> findByStatus(String status);
    List<CourtCase> findByCaseType(String caseType);
    boolean existsByCaseNumber(String caseNumber);
}
