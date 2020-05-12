package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationDto {
    private Long Id;
    private String name;
    private String disease;
    private String dosePerKg;
    private Unit unit;
    private boolean mandatory;
    private long intervalInWeeks;
    private List<Visit_VaccinationDto> visit_vaccinationsDtoList;

    public VaccinationDto(String name, String disease, String dosePerKg, Unit unit, boolean mandatory, long intervalInWeeks) {
        this.name = name;
        this.disease = disease;
        this.dosePerKg = dosePerKg;
        this.unit = unit;
        this.mandatory = mandatory;
        this.intervalInWeeks = intervalInWeeks;
    }
}
