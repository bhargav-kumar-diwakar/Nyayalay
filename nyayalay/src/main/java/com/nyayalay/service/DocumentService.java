package com.nyayalay.service;

import com.nyayalay.dto.DocumentDTO;

import java.util.List;

public interface DocumentService {
    DocumentDTO addDocument(DocumentDTO documentDTO);
    DocumentDTO getDocumentById(Long id);
    List<DocumentDTO> getDocumentsByCaseId(Long caseId);
    List<DocumentDTO> getAllDocuments();
    void deleteDocument(Long id);
}
