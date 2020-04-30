package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChronicDisease_PetDto {
    private Long id;
    private Long petId;
    private Long chronicDiseaseId;
    private LocalDate dateOfDiagnosis;

    public ChronicDisease_PetDto(Long petId, Long chronicDiseaseId, LocalDate dateOfDiagnosis) {
        this.petId = petId;
        this.chronicDiseaseId = chronicDiseaseId;
        this.dateOfDiagnosis = dateOfDiagnosis;
    }
}

