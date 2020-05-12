package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VisitDto {
    private Long id;
    private LocalDate dateOfVisit;
    private Long petId;
    private String diagnose;
    private String additionalRecommendation;
    private int weight;
//    private List<Visit_MedicationDto> visit_medicationsDtoList;
//    private List<Visit_VaccinationDto> visit_vaccinationsDtoList;

    public VisitDto(Long petId, String diagnose, String additionalRecommendation, int weight) {
        this.petId = petId;
        this.diagnose = diagnose;
        this.additionalRecommendation = additionalRecommendation;
        this.weight = weight;
    }
}
