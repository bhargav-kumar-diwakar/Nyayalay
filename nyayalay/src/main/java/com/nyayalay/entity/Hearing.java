package com.nyayalay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="hearings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hearing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate hearingDate;
    private String location;
    private String judgeName;
    private String outcome;
    private LocalDate nextHearingDate;

}
