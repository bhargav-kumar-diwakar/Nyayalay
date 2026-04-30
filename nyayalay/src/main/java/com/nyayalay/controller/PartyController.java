package com.nyayalay.controller;

import com.nyayalay.dto.PartyDTO;
import com.nyayalay.service.PartyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parties")
public class PartyController {
    @Autowired
    private PartyService partyService;

    @PostMapping
    public ResponseEntity<PartyDTO> addParty(@Valid @RequestBody PartyDTO partyDTO){
        return new ResponseEntity<>(partyService.addParty(partyDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PartyDTO>> getAllParties(){
        return ResponseEntity.ok(partyService.getAllParties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartyDTO> getPartyById(@PathVariable Long id){
        return ResponseEntity.ok(partyService.getPartyById(id));
    }

    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<PartyDTO>> getPartiesByCaseId(@PathVariable Long caseId){
        return ResponseEntity.ok(partyService.getPartiesByCaseId(caseId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartyDTO> updateParty(@PathVariable Long id, @Valid @RequestBody PartyDTO partyDTO){
        return ResponseEntity.ok(partyService.updateParty(id,partyDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParty(@PathVariable Long id){
        partyService.deleteParty(id);
        return ResponseEntity.ok("Party Deleted Successfully");
    }

}
