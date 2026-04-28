package com.nyayalay.controller;

import com.nyayalay.dto.CaseDTO;
import com.nyayalay.service.CaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class CaseController{

    @Autowired
    private CaseService caseService;

    @PostMapping
    public ResponseEntity<CaseDTO> createCase(@Valid @RequestBody CaseDTO caseDTO){
        return new ResponseEntity<>(caseService.createCase(caseDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CaseDTO>>  getAllCases(){
        return ResponseEntity.ok(caseService.getAllCases());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaseDTO> getCaseById(@PathVariable Long id){
        return ResponseEntity.ok(caseService.getCaseById(id));
    }

    @GetMapping("/number/{caseNumber}")
    public ResponseEntity<CaseDTO> getCaseByCaseNumber(@PathVariable String caseNumber){
        return ResponseEntity.ok(caseService.getCaseByCaseNumber(caseNumber));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CaseDTO>> getCasesByStatus(@PathVariable String status){
        return ResponseEntity.ok(caseService.getCasesByStatus(status));
    }

    @GetMapping("/type/{caseType}")
    public ResponseEntity<List<CaseDTO>> getCasesByType(@PathVariable String caseType){
        return ResponseEntity.ok(caseService.getCasesByType(caseType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaseDTO> updateCase(@PathVariable Long id, @Valid @RequestBody CaseDTO caseDTO){
        return ResponseEntity.ok(caseService.updateCase(id,caseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCase(@PathVariable Long id){
        caseService.deleteCase(id);
        return ResponseEntity.ok("Case deleted successfully");
    }

}
