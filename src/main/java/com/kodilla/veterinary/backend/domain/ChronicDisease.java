package com.kodilla.veterinary.backend.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
@NamedNativeQuery(name = "ChronicDisease.filterChronicDiseases",
        query = "SELECT * FROM CHRONIC_DISEASE WHERE NAME LIKE CONCAT('%', :KEYWORD , '%')",
        resultClass = ChronicDisease.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    public ChronicDisease(Long id,@NotNull String name) {
        this.id=id;
        this.name = name;
    }

}
