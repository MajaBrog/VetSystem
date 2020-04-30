package com.kodilla.veterinary.backend.mapper;

import com.kodilla.veterinary.backend.controller.RecordNotFoundException;
import com.kodilla.veterinary.backend.domain.Pet;
import com.kodilla.veterinary.backend.domain.PetDto;
import com.kodilla.veterinary.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetMapper {
    @Autowired
    ChronicDiseaseMapper chronicDiseaseMapper;
    @Autowired
    VisitMapper visitMapper;
    @Autowired
    ClientService clientService;

    public Pet mapToPet(final PetDto petDto){
        return new Pet(
                petDto.getChipId(),
                petDto.getName(),
                petDto.getKind(),
                petDto.getBirthDate(),
                petDto.isSterilised(),
                petDto.getDateOfSterilization(),
                petDto.isAggressive(),
                clientService.getClient(petDto.getClientId()).orElseThrow(RecordNotFoundException::new));
    }

    public PetDto mapToPetDto(final Pet pet) {
        System.out.println(chronicDiseaseMapper.mapToChronicDiseaseDtoList(pet.getChronicDiseases()));
        return new PetDto(
                pet.getId(),
                pet.getChipId(),
                pet.getName(),
                pet.getKind(),
                visitMapper.mapToVisitDtoList(pet.getVisits()),
                pet.getBirthDate(),
                chronicDiseaseMapper.mapToChronicDiseaseDtoList(pet.getChronicDiseases()),
                pet.isSterilised(),
                pet.getDateOfSterilization(),
                pet.isAggressive(),
                pet.getClient().getId());
    }

    public List<PetDto> mapToPetDtoList(final List<Pet> petList){
        return petList.stream()
                .map(p->new PetDto(
                        p.getId(),
                        p.getChipId(),
                        p.getName(),
                        p.getKind(),
                        visitMapper.mapToVisitDtoList(p.getVisits()),
                        p.getBirthDate(),
                        chronicDiseaseMapper.mapToChronicDiseaseDtoList(p.getChronicDiseases()),
                        p.isSterilised(),
                        p.getDateOfSterilization(),
                        p.isAggressive(),
                        p.getClient().getId()))
                .collect(Collectors.toList());
    }
}