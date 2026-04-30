package com.nyayalay.controller;

import com.nyayalay.dto.DocumentDTO;
import com.nyayalay.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentDTO> addDocument(@RequestBody DocumentDTO documentDTO){
        return new ResponseEntity<>(documentService.addDocument(documentDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DocumentDTO>> getAllDocuments(){
        return ResponseEntity.ok(documentService.getAllDocuments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getDocumentById(@PathVariable Long id){
        return ResponseEntity.ok(documentService.getDocumentById(id));
    }

    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<DocumentDTO>> getDocumentsByCaseId(@PathVariable Long caseId){
        return ResponseEntity.ok(documentService.getDocumentsByCaseId(caseId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long id){
        documentService.deleteDocument(id);
        return ResponseEntity.ok("Document deleted sucessfully");
    }
}
