package com.kodilla.veterinary.backend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@Data
@Entity

public class Visit_Vaccination {
    @Id
    @GeneratedValue
    @Column(name = "VISIT_VACCINATION_ID")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Visit visit;
    @ManyToOne(fetch = FetchType.LAZY)
    private Vaccination vaccination;
    @NotNull
    private String dose;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Unit unit;

    public Visit_Vaccination(Visit visit, Vaccination vaccination, @NotNull String dose, @NotNull Unit unit) {
        this.visit = visit;
        this.vaccination = vaccination;
        this.dose = dose;
        this.unit = unit;
    }
}
