package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@NamedNativeQuery(name = "Vaccination.filterVaccinations",
        query = "SELECT * FROM VACCINATION WHERE NAME LIKE CONCAT('%', :KEYWORD , '%')",
        resultClass = Vaccination.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String disease;
    @NotNull
    private String dosePerKg;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Unit unit;
    @NotNull
    private boolean mandatory;
    @NotNull
    private long intervalInWeeks;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vaccination")
    private List<Visit_Vaccination> visit_vaccinations = new ArrayList<>();

    public Vaccination(@NotNull String name, @NotNull String disease, @NotNull String dosePerKg, @NotNull Unit unit, @NotNull boolean mandatory, @NotNull long intervalInWeeks) {
        this.name = name;
        this.disease = disease;
        this.dosePerKg = dosePerKg;
        this.unit = unit;
        this.mandatory = mandatory;
        this.intervalInWeeks = intervalInWeeks;
    }
    public Vaccination(Long id, @NotNull String name, @NotNull String disease, @NotNull String dosePerKg, @NotNull Unit unit, @NotNull boolean mandatory, @NotNull long intervalInWeeks) {
        this.id=id;
        this.name = name;
        this.disease = disease;
        this.dosePerKg = dosePerKg;
        this.unit = unit;
        this.mandatory = mandatory;
        this.intervalInWeeks = intervalInWeeks;
    }
}

