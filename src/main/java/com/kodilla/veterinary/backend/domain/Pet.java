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
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String chipId;
    private String name;
    private String kind;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Visit> visits;
    private LocalDate birthDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ChronicDisease> chronicDiseases = new ArrayList<>();
    @NotNull
    private boolean sterilised;
    private LocalDate dateOfSterilization;
    private boolean aggressive;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    public Pet(String chipId, String name, String kind, LocalDate birthDate, @NotNull boolean sterilised, LocalDate dateOfSterilization, boolean aggressive, Client client) {
        this.chipId = chipId;
        this.name = name;
        this.kind = kind;
        this.birthDate = birthDate;
        this.sterilised = sterilised;
        this.dateOfSterilization = dateOfSterilization;
        this.aggressive = aggressive;
        this.client = client;
    }
}
