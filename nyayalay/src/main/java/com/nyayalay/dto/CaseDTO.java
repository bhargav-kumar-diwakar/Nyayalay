package com.nyayalay.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseDTO {
    private Long id;

    @NotBlank(message = "Case number is required")
    private String caseNumber;

    @NotBlank(message = "Title is required")
    private String title;

    private String caseType;
    private String status;
    private String description;
    private LocalDate filedDate;

}
