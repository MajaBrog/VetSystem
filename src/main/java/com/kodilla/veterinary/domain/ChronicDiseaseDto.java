package com.kodilla.veterinary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChronicDiseaseDto {
    private Long id;
    private String name;
    private LocalDate dateOfDiagnosis;
    private Long petId;

    public ChronicDiseaseDto(String name, LocalDate dateOfDiagnosis, Long petId) {
        this.name = name;
        this.dateOfDiagnosis = dateOfDiagnosis;
        this.petId = petId;
    }
}
