package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;


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
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "pet")
    private List<Visit> visits;
    private LocalDate birthDate;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "pet")
    private List<ChronicDisease_Pet> chronicDiseases_Pet = new ArrayList<>();
    @NotNull
    private boolean sterilised;
    private LocalDate dateOfSterilization;
    private boolean aggressive;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

//    public Pet(String chipId, String name, String kind, LocalDate birthDate, @NotNull boolean sterilised, LocalDate dateOfSterilization, boolean aggressive, Client client) {
//        this.chipId = chipId;
//        this.name = name;
//        this.kind = kind;
//        this.birthDate = birthDate;
//        this.sterilised = sterilised;
//        this.dateOfSterilization = dateOfSterilization;
//        this.aggressive = aggressive;
//        this.client = client;
//    }
//    public Pet(Long id,String chipId, String name, String kind, LocalDate birthDate, @NotNull boolean sterilised, LocalDate dateOfSterilization, boolean aggressive, Client client) {
//        this.id=id;
//        this.chipId = chipId;
//        this.name = name;
//        this.kind = kind;
//        this.birthDate = birthDate;
//        this.sterilised = sterilised;
//        this.dateOfSterilization = dateOfSterilization;
//        this.aggressive = aggressive;
//        this.client = client;
//    }
}
