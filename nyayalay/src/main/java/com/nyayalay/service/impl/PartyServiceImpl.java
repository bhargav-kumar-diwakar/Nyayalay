package com.nyayalay.service.impl;

import com.nyayalay.dto.PartyDTO;
import com.nyayalay.entity.CourtCase;
import com.nyayalay.entity.Party;
import com.nyayalay.repository.CaseRepository;
import com.nyayalay.repository.PartyRepository;
import com.nyayalay.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartyServiceImpl implements PartyService {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private CaseRepository caseRepository;

    @Override
    public PartyDTO addParty(PartyDTO partyDTO){
        CourtCase courtCase = caseRepository.findById(partyDTO.getCaseId())
                .orElseThrow(()-> new RuntimeException("Case not found with id: "+partyDTO.getCaseId()));
        return mapToDTO(partyRepository.save(mapToEntity(partyDTO,courtCase)));
    }

    @Override
    public PartyDTO getPartyById(Long id){
        Party party = partyRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Party not found with id: "+id));
        return mapToDTO(party);
    }

    @Override
    public List<PartyDTO> getPartiesByCaseId(Long caseId){
        return partyRepository.findByCourtCaseId(caseId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PartyDTO> getAllParties(){
        return partyRepository.findAll()
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public PartyDTO updateParty(Long id,PartyDTO partyDTO){
        Party existing = partyRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Party not found with id: "+id));
        existing.setName(partyDTO.getName());
        existing.setRole(partyDTO.getRole());
        existing.setContactNumber(partyDTO.getContactNumber());
        existing.setEmail(partyDTO.getEmail());
        existing.setAddress(partyDTO.getAddress());
        return mapToDTO(partyRepository.save(existing));
    }

    @Override
    public void deleteParty(Long id){
        Party party = partyRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Party not found with id: "+id));
        partyRepository.delete(party);
    }

    private Party mapToEntity(PartyDTO dto, CourtCase courtCase){
        Party p = new Party();
        p.setName(dto.getName());
        p.setRole(dto.getRole());
        p.setContactNumber(dto.getContactNumber());
        p.setEmail(dto.getAddress());
        p.setCourtCase(courtCase);
        return p;
    }

    private PartyDTO mapToDTO(Party p){
        PartyDTO dto = new PartyDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setRole(p.getRole());
        dto.setContactNumber(p.getContactNumber());
        dto.setEmail(p.getEmail());
        dto.setAddress(p.getAddress());
        dto.setCaseId(p.getCourtCase().getId());
        return dto;
    }
}
