package com.nyayalay.service;

import com.nyayalay.dto.PartyDTO;

import java.util.List;

public interface PartyService {
    PartyDTO addParty(PartyDTO partyDTO);
    PartyDTO getPartyById(Long id);
    List<PartyDTO> getPartiesByCaseId(Long caseId);
    List<PartyDTO> getAllParties();
    PartyDTO updateParty(Long id, PartyDTO partyDTO);
    void deleteParty(Long id);
}
