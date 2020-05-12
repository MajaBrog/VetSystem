package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit_VaccinationDto {
    private Long id;
    private Long visitId;
    private Long vaccinationId;
    private String dose;
    private Unit unit;
    private LocalDate remindDate;

    public Visit_VaccinationDto(Long visitId, Long vaccinationId, String dose, Unit unit) {
        this.visitId = visitId;
        this.vaccinationId = vaccinationId;
        this.dose = dose;
        this.unit = unit;
    }
}
