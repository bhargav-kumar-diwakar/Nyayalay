package com.nyayalay.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HearingDTO {
    private Long id;

    @NotNull(message = "Hearing date is required")
    private LocalDate hearingDate;

    private String location;
    private String judgeName;
    private String outcome;
    private String nextHearingDate;
    private Long caseId;

}
