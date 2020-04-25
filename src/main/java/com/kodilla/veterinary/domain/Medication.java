package com.kodilla.veterinary.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity

public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String medicationName;
    @NotNull
    private String dosePerKg;
    @NotNull
    private Unit unit;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "medication")
    private List<Visit_Medication> visit_medications = new ArrayList<>();

    public Medication(@NotNull String medicationName, @NotNull String dosePerKg, @NotNull Unit unit) {
        this.medicationName = medicationName;
        this.dosePerKg = dosePerKg;
        this.unit = unit;
    }
}
