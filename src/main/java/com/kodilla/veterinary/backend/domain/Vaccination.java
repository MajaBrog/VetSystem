package com.kodilla.veterinary.backend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotNull
    private String name;
    @NotNull
    private String diseases;
    @NotNull
    private String dosePerKg;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Unit unit;
    @NotNull
    private boolean mandatory;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vaccination")
    private List<Visit_Vaccination> visit_vaccinations = new ArrayList<>();

    public Vaccination(@NotNull String name, @NotNull String diseases, @NotNull String dosePerKg, @NotNull Unit unit, @NotNull boolean mandatory) {
        this.name = name;
        this.diseases = diseases;
        this.dosePerKg = dosePerKg;
        this.unit = unit;
        this.mandatory = mandatory;
    }
}
