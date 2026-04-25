package com.nyayalay.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyDTO {
    private Long id;

    @NotBlank(message = "Party name is required")
    private String name;

    private String role;
    private String contactNumber;
    private String email;
    private String address;
    private Long caseId;
}
