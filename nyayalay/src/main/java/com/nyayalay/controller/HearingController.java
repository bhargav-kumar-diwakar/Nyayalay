package com.nyayalay.controller;

import com.nyayalay.dto.HearingDTO;
import com.nyayalay.service.HearingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hearings")
public class HearingController {

    @Autowired
    private HearingService hearingService;

    @PostMapping
    public ResponseEntity<HearingDTO> scheduleHearing(@Valid @RequestBody HearingDTO hearingDTO){
        return new ResponseEntity<>(hearingService.scheduleHearing(hearingDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HearingDTO>> getAllHearings(){
        return ResponseEntity.ok(hearingService.getAllHearings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HearingDTO> getHearingById(@PathVariable Long id){
        return ResponseEntity.ok(hearingService.getHearingById(id));
    }

    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<HearingDTO>> getHearingsByCaseId(@PathVariable Long caseId){
        return ResponseEntity.ok(hearingService.getHearingsByCaseId(caseId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HearingDTO> updateHearing(@PathVariable Long id, @Valid @RequestBody HearingDTO hearingDTO){
        return ResponseEntity.ok(hearingService.updateHearing(id,hearingDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHearing(@PathVariable Long id){
        hearingService.deleteHearing(id);
        return ResponseEntity.ok("Hearing deleted successfully");
    }

}
