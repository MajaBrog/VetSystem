package com.kodilla.veterinary.backend.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChronicDisease_Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pet pet;
    @ManyToOne(fetch = FetchType.LAZY)
    private ChronicDisease chronicDisease;
    private LocalDate dateOfDiagnosis;

    public ChronicDisease_Pet(Pet pet, ChronicDisease chronicDisease, LocalDate dateOfDiagnosis) {
        this.pet = pet;
        this.chronicDisease = chronicDisease;
        this.dateOfDiagnosis = dateOfDiagnosis;
    }
}
