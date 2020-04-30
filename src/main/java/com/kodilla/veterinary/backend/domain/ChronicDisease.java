package com.kodilla.veterinary.backend.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @OneToMany( targetEntity = ChronicDisease_Pet.class, mappedBy = "chronicDisease",fetch = FetchType.LAZY)
    private List<ChronicDisease_Pet> ChronicDisease_Pets;

    public ChronicDisease(@NotNull String name) {
        this.name = name;
    }
}
