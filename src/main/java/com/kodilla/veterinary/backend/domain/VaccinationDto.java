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
    private String diseases;
    private String dosePerKg;
    private Unit unit;
    private boolean mandatory;
    private List<Visit_VaccinationDto> visit_vaccinationsDtoList;

    public VaccinationDto(String name, String diseases, String dosePerKg, Unit unit, boolean mandatory) {
        this.name = name;
        this.diseases = diseases;
        this.dosePerKg = dosePerKg;
        this.unit = unit;
        this.mandatory = mandatory;
    }
}
