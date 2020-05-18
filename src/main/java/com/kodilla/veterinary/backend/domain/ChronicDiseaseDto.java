package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChronicDiseaseDto {
    private Long id;
    private String name;
    private List<ChronicDisease_PetDto> chronicDisease_PetDtoList = new ArrayList<>();

    public ChronicDiseaseDto(String name) {
        this.name = name;
    }
}
