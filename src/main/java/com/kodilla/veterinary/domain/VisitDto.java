package com.kodilla.veterinary.domain;

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
    private String AdditionalRecommendation;
    private int weight;

    public VisitDto(Long petId, String diagnose, String additionalRecommendation, int weight) {
        this.petId = petId;
        this.diagnose = diagnose;
        AdditionalRecommendation = additionalRecommendation;
        this.weight = weight;
    }
}
