package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {
    private Long id;
    private String chipId;
    private String name;
    private String kind;
    private List<VisitDto> visitDtoList;
    private LocalDate birthDate;
    private List<ChronicDiseaseDto> chronicDiseaseDtoList;
    private boolean sterilised;
    private LocalDate dateOfSterilization;
    private boolean aggressive;
    private Long clientId;

    public PetDto(String chipId, String name, String kind, LocalDate birthDate, boolean sterilised, LocalDate dateOfSterilization, boolean aggressive, Long clientId) {
        this.chipId = chipId;
        this.name = name;
        this.kind = kind;
        this.birthDate = birthDate;
        this.sterilised = sterilised;
        this.dateOfSterilization = dateOfSterilization;
        this.aggressive = aggressive;
        this.clientId=clientId;
    }

}
