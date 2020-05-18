package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Visit_Medication {
    @Id
    @GeneratedValue
    @Column(name = "VISIT_MEDICATION_ID")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Visit visit;
    @ManyToOne(fetch = FetchType.LAZY)
    private Medication medication;
    @NotNull
    private String dose;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Unit unit;

    public Visit_Medication(Visit visit, Medication medication, @NotNull String dose, @NotNull Unit unit) {
        this.visit = visit;
        this.medication = medication;
        this.dose = dose;
        this.unit = unit;
    }
}
