package com.nyayalay.service;

import com.nyayalay.dto.CaseDTO;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface CaseService {
    CaseDTO createCase(CaseDTO caseDTO);
    CaseDTO getCaseById(Long id);
    CaseDTO getCaseByCaseNumber(String caseNumber);
    List<CaseDTO> getAllCases();
    List<CaseDTO> getCasesByStatus(String status);
    List<CaseDTO> getCasesByType(String caseType);
    CaseDTO updateCase(Long id, CaseDTO caseDTO);
    void deleteCase(Long id);
}
