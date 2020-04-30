package com.kodilla.veterinary.backend.mapper;
import com.kodilla.veterinary.backend.domain.ChronicDisease;
import com.kodilla.veterinary.backend.domain.ChronicDiseaseDto;
import com.kodilla.veterinary.backend.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChronicDiseaseMapper {
    @Autowired
    private PetService petService;
    @Autowired
    ChronicDisease_PetMapper chronicDisease_petMapper;

    public ChronicDisease mapToChronicDisease(final ChronicDiseaseDto chronicDiseaseDto){
        return new ChronicDisease(
                chronicDiseaseDto.getName());
    }

    public ChronicDiseaseDto mapToChronicDiseaseDto(final ChronicDisease chronicDisease) {
        return new ChronicDiseaseDto(
                chronicDisease.getId(),
                chronicDisease.getName(),
                chronicDisease_petMapper.mapToChronicDisease_PetDtoList(chronicDisease.getChronicDisease_Pets()));
    }

    public List<ChronicDiseaseDto> mapToChronicDiseaseDtoList(final List<ChronicDisease> chronicDiseaseList){
        return chronicDiseaseList.stream()
                .map(c->new ChronicDiseaseDto(
                        c.getId(),
                        c.getName(),
                        chronicDisease_petMapper.mapToChronicDisease_PetDtoList(c.getChronicDisease_Pets())))
                .collect(Collectors.toList());
    }
}

