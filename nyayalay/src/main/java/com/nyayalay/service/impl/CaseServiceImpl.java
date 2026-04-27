package com.nyayalay.service.impl;

import com.nyayalay.dto.CaseDTO;
import com.nyayalay.entity.CourtCase;
import com.nyayalay.repository.CaseRepository;
import com.nyayalay.service.CaseService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseRepository caseRepository;

    @Override
    public CaseDTO createCase(CaseDTO caseDTO){
        CourtCase courtCase = mapToEntity(caseDTO);
        return mapToDTO(caseRepository.save(courtCase));
    }

    @Override
    public CaseDTO getCaseById(Long id){
        CourtCase courtCase = caseRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("case not found with id: "+id));
        return mapToDTO(courtCase);
    }

    @Override
    public CaseDTO getCaseByCaseNumber(String caseNumber){
        CourtCase courtCase = caseRepository.findByCaseNumber(caseNumber)
                .orElseThrow(()-> new RuntimeException("Case not found with number: "+caseNumber));
        return mapToDTO(courtCase);
    }

    @Override
    public List<CaseDTO> getAllCases(){
        return caseRepository.findAll()
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CaseDTO> getCasesByStatus(String status){
        return caseRepository.findByStatus(status)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CaseDTO> getCasesByType(String caseType){
        return caseRepository.findByCaseType(caseType)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CaseDTO updateCase(Long id,CaseDTO caseDTO){
        CourtCase existing = caseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found with id: "+id));
        existing.setTitle(caseDTO.getTitle());
        existing.setCaseType(caseDTO.getCaseType());
        existing.setStatus(caseDTO.getStatus());
        existing.setDescription(caseDTO.getDescription());
        existing.setFiledDate(caseDTO.getFiledDate());
        return mapToDTO(caseRepository.save(existing));
    }

    @Override
    public void deleteCase(Long id){
        CourtCase courtCase = caseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found with id: "+id));
        caseRepository.delete(courtCase);
    }

    private CourtCase mapToEntity(CaseDTO dto){
        CourtCase c = new CourtCase();
        c.setCaseNumber(dto.getCaseNumber());
        c.setTitle(dto.getTitle());
        c.setCaseType(dto.getCaseType());
        c.setStatus(dto.getStatus());
        c.setDescription(dto.getDescription());
        c.setFiledDate(dto.getFiledDate());
        return c;
    }

    private CaseDTO mapToDTO(CourtCase c){
        CaseDTO dto = new CaseDTO();
        dto.setId(c.getId());
        dto.setCaseNumber(c.getCaseNumber());
        dto.setTitle(c.getTitle());
        dto.setCaseType(c.getCaseType());
        dto.setStatus(c.getStatus());
        dto.setDescription(c.getDescription());
        dto.setFiledDate(c.getFiledDate());
        return dto;
    }
}
