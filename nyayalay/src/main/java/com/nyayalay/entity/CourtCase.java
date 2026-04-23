package com.nyayalay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "court_cases")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourtCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String caseNumber;

    @Column(nullable = false)
    private String title;
    private String caseType;
    private String status;
    private String description;
    private LocalDate filedDate;
}
