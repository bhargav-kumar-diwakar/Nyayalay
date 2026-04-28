package com.nyayalay.service.impl;

import com.nyayalay.dto.DocumentDTO;
import com.nyayalay.entity.CourtCase;
import com.nyayalay.entity.Document;
import com.nyayalay.repository.CaseRepository;
import com.nyayalay.repository.DocumentRepository;
import com.nyayalay.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private CaseRepository caseRepository;

    @Override
    public DocumentDTO addDocument(DocumentDTO documentDTO){
        CourtCase courtCase = caseRepository.findById(documentDTO.getCaseId())
                .orElseThrow(()-> new RuntimeException("Case not found with id: "+documentDTO));
        Document document = mapToEntity(documentDTO,courtCase);
        document.setUploadDate(LocalDate.now());
        return mapToDTO(documentRepository.save(document));
    }

    @Override
    public DocumentDTO getDocumentById(Long id){
        Document document = documentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Document not found with id: "+id));
        return mapToDTO(document);
    }

    @Override
    public List<DocumentDTO> getDocumentsByCaseId(Long caseId){
        return documentRepository.findByCourtCaseId(caseId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DocumentDTO> getAllDocuments(){
        return documentRepository.findAll()
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteDocument(Long id){
        Document document = documentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Document not found with id: "+id));
        documentRepository.delete(document);
    }


    private Document mapToEntity(DocumentDTO dto, CourtCase courtCase){
        Document d = new Document();
        d.setFileName(dto.getFileName());
        d.setFileType(dto.getFileType());
        d.setFileUrl(dto.getFileUrl());
        d.setCourtCase(courtCase);
        return d;
    }

    private DocumentDTO mapToDTO(Document d){
        DocumentDTO dto = new DocumentDTO();
        dto.setId(d.getId());
        dto.setFileName(d.getFileName());
        dto.setFileType(d.getFileType());
        dto.setFileUrl(d.getFileUrl());
        dto.setUploadDate(d.getUploadDate());
        dto.setCaseId(d.getCourtCase().getId());
        return dto;
    }
}
