package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String chipId;
    private String name;
    private String kind;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pet")
    private List<Visit> visits;
    private LocalDate birthDate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pet")
    private List<ChronicDisease_Pet> chronicDiseases_Pet = new ArrayList<>();
    @NotNull
    private boolean sterilised;
    private LocalDate dateOfSterilization;
    private boolean aggressive;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

}
