package com.nyayalay.service;

import com.nyayalay.dto.HearingDTO;

import java.util.List;

public interface HearingService {
    HearingDTO scheduleHearing(HearingDTO hearingDTO);
    HearingDTO getHearingById(Long id);
    List<HearingDTO> getHearingsByCaseId(Long caseId);
    List<HearingDTO> getAllHearings();
    HearingDTO updateHearing(Long id, HearingDTO hearingDTO);
    void deleteHearing(Long id);
}
