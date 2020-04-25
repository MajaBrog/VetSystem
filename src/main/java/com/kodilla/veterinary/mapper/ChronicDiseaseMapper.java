package com.kodilla.veterinary.mapper;
import com.kodilla.veterinary.controller.RecordNotFoundException;
import com.kodilla.veterinary.domain.ChronicDisease;
import com.kodilla.veterinary.domain.ChronicDiseaseDto;
import com.kodilla.veterinary.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChronicDiseaseMapper {
    @Autowired
    private PetService petService;

    public ChronicDisease mapToChronicDisease(final ChronicDiseaseDto chronicDiseaseDto){
        return new ChronicDisease(
                chronicDiseaseDto.getName(),
                chronicDiseaseDto.getDateOfDiagnosis(),
                petService.getPet(chronicDiseaseDto.getPetId()).orElseThrow(RecordNotFoundException::new));
    }

    public ChronicDiseaseDto mapToChronicDiseaseDto(final ChronicDisease chronicDisease) {
        return new ChronicDiseaseDto(
                chronicDisease.getId(),
                chronicDisease.getName(),
                chronicDisease.getDateOfDiagnosis(),
                chronicDisease.getPet().getId());
    }

    public List<ChronicDiseaseDto> mapToChronicDiseaseDtoList(final List<ChronicDisease> chronicDiseaseList){
        return chronicDiseaseList.stream()
                .map(c->new ChronicDiseaseDto(
                        c.getId(),
                        c.getName(),
                        c.getDateOfDiagnosis(),
                        c.getPet().getId()))
                .collect(Collectors.toList());
    }
}

