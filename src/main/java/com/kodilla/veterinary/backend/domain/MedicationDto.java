package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDto {
    private Long id;
    private String medicationName;
    private String dosePerKg;
    private Unit unit;
    private List<Visit_MedicationDto> visit_medicationDtoList;

    public MedicationDto(String medicationName, String dosePerKg, Unit unit) {
        this.medicationName = medicationName;
        this.dosePerKg = dosePerKg;
        this.unit = unit;
    }
}
