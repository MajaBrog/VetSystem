package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
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
    private List<ChronicDisease_PetDto> chronicDisease_PetDtoList;
    private boolean sterilised;
    private LocalDate dateOfSterilization;
    private boolean aggressive;
    private Long clientId;

}
