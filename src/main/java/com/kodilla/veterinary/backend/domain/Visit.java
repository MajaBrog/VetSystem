package com.kodilla.veterinary.backend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private LocalDate dateOfVisit;
    @ManyToOne
    private Pet pet;
    @NotNull
    private String diagnose;
    @Column
    private String additionalRecommendation;
    private int weight;
    @OneToMany( targetEntity = Visit_Medication.class, mappedBy = "visit",fetch = FetchType.LAZY)
    private List<Visit_Medication> visit_medications = new ArrayList<>();
    @OneToMany( targetEntity = Visit_Vaccination.class, mappedBy = "visit",fetch = FetchType.LAZY)
    private List<Visit_Vaccination> visit_vaccinations = new ArrayList<>();



    public Visit(Pet pet, @NotNull String diagnose, String additionalRecommendation, int weight) {
        this.dateOfVisit = LocalDate.now();
        this.pet = pet;
        this.diagnose = diagnose;
        this.additionalRecommendation = additionalRecommendation;
        this.weight = weight;
    }
}
