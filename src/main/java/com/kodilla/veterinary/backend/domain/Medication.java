package com.kodilla.veterinary.backend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@NamedNativeQuery(name = "Medication.filterMedications",
        query = "SELECT * FROM VACCINATION WHERE MEDICATION_NAME LIKE CONCAT('%', :KEYWORD , '%')",
        resultClass = Medication.class)
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
    @Enumerated(EnumType.STRING)
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
