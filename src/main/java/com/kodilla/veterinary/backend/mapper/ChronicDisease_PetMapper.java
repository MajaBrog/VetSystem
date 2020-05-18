package com.kodilla.veterinary.backend.mapper;

import com.kodilla.veterinary.backend.controller.RecordNotFoundException;
import com.kodilla.veterinary.backend.domain.ChronicDisease_Pet;
import com.kodilla.veterinary.backend.domain.ChronicDisease_PetDto;
import com.kodilla.veterinary.backend.service.ChronicDiseaseService;
import com.kodilla.veterinary.backend.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChronicDisease_PetMapper {
    @Autowired
    PetService petService;
    @Autowired
    ChronicDiseaseService chronicDiseaseService;

    public ChronicDisease_Pet mapToChronicDisease_Pet(final ChronicDisease_PetDto chronicDisease_PetDto) {
        return new ChronicDisease_Pet(
                petService.getPet(chronicDisease_PetDto.getPetId()).orElseThrow(RecordNotFoundException::new),
                chronicDiseaseService.getChronicDisease(chronicDisease_PetDto.getChronicDiseaseId()).orElseThrow(RecordNotFoundException::new),
                chronicDisease_PetDto.getDateOfDiagnosis());
    }

    public List<ChronicDisease_PetDto> mapToChronicDisease_PetDtoList(final List<ChronicDisease_Pet> chronicDisease_PetList) {
        return chronicDisease_PetList.stream()
                .map(c -> new ChronicDisease_PetDto(
                        c.getId(),
                        c.getPet().getId(),
                        c.getChronicDisease().getId(),
                        c.getDateOfDiagnosis()))
                .collect(Collectors.toList());
    }

}
