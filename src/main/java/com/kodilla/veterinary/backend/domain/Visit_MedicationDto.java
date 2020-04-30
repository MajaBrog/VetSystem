package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Visit_MedicationDto {
    private Long id;
    private Long visitId;
    private Long medicationId;
    private String dose;
    private Unit unit;

    public Visit_MedicationDto(Long visitId, Long medicationId, String dose, Unit unit) {
        this.visitId = visitId;
        this.medicationId = medicationId;
        this.dose = dose;
        this.unit = unit;
    }
}
