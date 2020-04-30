package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Visit_VaccinationDto {
    private Long id;
    private Long visitId;
    private Long vaccinationId;
    private String dose;
    private Unit unit;

    public Visit_VaccinationDto(Long visitId, Long vaccinationId, String dose, Unit unit) {
        this.visitId = visitId;
        this.vaccinationId = vaccinationId;
        this.dose = dose;
        this.unit = unit;
    }
}
