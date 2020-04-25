package com.kodilla.veterinary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDto {
    private Long id;
    private String medicationName;
    private String dosePerKg;
    private Unit unit;

    public MedicationDto(String medicationName, String dosePerKg, Unit unit) {
        this.medicationName = medicationName;
        this.dosePerKg = dosePerKg;
        this.unit = unit;
    }
}
