package com.kodilla.veterinary.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@Data
@Embeddable
public class Visit_Medication {
    @Id
    @GeneratedValue
    @Column(name = "VISIT_MEDICATION_ID")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Visit visit;
    @ManyToOne(fetch = FetchType.LAZY)
    private Medication medication;
    @NotNull
    private String dose;
    @NotNull
    private Unit unit;
}
