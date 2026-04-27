package com.nyayalay.service.impl;

import com.nyayalay.dto.HearingDTO;
import com.nyayalay.entity.CourtCase;
import com.nyayalay.entity.Hearing;
import com.nyayalay.repository.CaseRepository;
import com.nyayalay.repository.HearingRepository;
import com.nyayalay.service.HearingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HearingServiceImpl implements HearingService {

    @Autowired
    private HearingRepository hearingRepository;

    @Autowired
    private CaseRepository caseRepository;

    @Override
    public HearingDTO scheduleHearing(HearingDTO hearingDTO){
        CourtCase courtCase = caseRepository.findById(hearingDTO.getCaseId())
                .orElseThrow(()-> new RuntimeException("Case not found with id: "+hearingDTO.getCaseId()));
        return mapToDTO(hearingRepository.save(mapToEntity(hearingDTO,courtCase)));
    }

    @Override
    public HearingDTO getHearingById(Long id){
        Hearing hearing = hearingRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Hearing not found with id: "+id));
        return mapToDTO(hearing);
    }

    @Override
    public List<HearingDTO> getHearingsByCaseId(Long caseId){
        return hearingRepository.findByCourtCaseId(caseId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<HearingDTO> getAllHearings(){
        return hearingRepository.findAll()
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public HearingDTO updateHearing(Long id, HearingDTO hearingDTO){
        Hearing existing = hearingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hearing not found with id: "+id));
        existing.setHearingDate(hearingDTO.getHearingDate());
        existing.setLocation(hearingDTO.getLocation());
        existing.setJudgeName(hearingDTO.getJudgeName());
        existing.setOutcome(hearingDTO.getOutcome());
        existing.setNextHearingDate((hearingDTO.getNextHearingDate()));
        return mapToDTO(hearingRepository.save(existing));
    }

    @Override
    public void deleteHearing(Long id){
        Hearing hearing = hearingRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Hearing not found with id: "+id));
        hearingRepository.delete(hearing);
    }

    private Hearing mapToEntity(HearingDTO dto, CourtCase courtCase){
        Hearing h = new Hearing();
        h.setHearingDate(dto.getHearingDate());
        h.setLocation(dto.getLocation());
        h.setJudgeName(dto.getJudgeName());
        h.setOutcome(dto.getOutcome());
        h.setNextHearingDate(dto.getNextHearingDate());
        h.setCourtCase(courtCase);
        return h;
    }

    private HearingDTO mapToDTO(Hearing h){
        HearingDTO dto = new HearingDTO();
        dto.setId(h.getId());
        dto.setHearingDate(h.getHearingDate());
        dto.setLocation(h.getLocation());
        dto.setJudgeName(h.getJudgeName());
        dto.setOutcome(h.getOutcome());
        dto.setNextHearingDate(h.getNextHearingDate());
        dto.setCaseId(h.getCourtCase().getId());
        return dto;
    }
}
