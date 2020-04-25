package com.kodilla.veterinary.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class ChronicDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    private LocalDate dateOfDiagnosis;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pet pet;

    public ChronicDisease(@NotNull String name, LocalDate dateOfDiagnosis, Pet pet) {
        this.name = name;
        this.dateOfDiagnosis = dateOfDiagnosis;
        this.pet = pet;
    }
}
