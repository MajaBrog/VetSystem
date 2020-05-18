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
    ChronicDisease_PetMapper chronicDisease_petMapper;
    @Autowired
    VisitMapper visitMapper;
    @Autowired
    ClientService clientService;

    public Pet mapToPet(final PetDto petDto) {
        return Pet.builder()
                .chipId(petDto.getChipId())
                .name(petDto.getName())
                .kind(petDto.getKind())
                .birthDate(petDto.getBirthDate())
                .sterilised(petDto.isSterilised())
                .dateOfSterilization(petDto.getDateOfSterilization())
                .aggressive(petDto.isAggressive())
                .client(clientService.getClient(petDto.getClientId()).orElseThrow(RecordNotFoundException::new))
                .build();
    }

    public Pet mapToUpdatedPet(final PetDto petDto) {
        return Pet.builder()
                .id(petDto.getId())
                .chipId(petDto.getChipId())
                .name(petDto.getName())
                .kind(petDto.getKind())
                .birthDate(petDto.getBirthDate())
                .sterilised(petDto.isSterilised())
                .dateOfSterilization(petDto.getDateOfSterilization())
                .aggressive(petDto.isAggressive())
                .client(clientService.getClient(petDto.getClientId()).orElseThrow(RecordNotFoundException::new))
                .build();
    }

    public PetDto mapToPetDto(final Pet pet) {
        return PetDto.builder()
                .id(pet.getId())
                .chipId(pet.getChipId())
                .name(pet.getName())
                .kind(pet.getKind())
                .visitDtoList(visitMapper.mapToVisitDtoList(pet.getVisits()))
                .birthDate(pet.getBirthDate())
                .chronicDisease_PetDtoList(chronicDisease_petMapper.mapToChronicDisease_PetDtoList(pet.getChronicDiseases_Pet()))
                .sterilised(pet.isSterilised())
                .dateOfSterilization(pet.getDateOfSterilization())
                .aggressive(pet.isAggressive())
                .clientId(pet.getClient().getId())
                .build();
    }

    public List<PetDto> mapToPetDtoList(final List<Pet> petList) {
        return petList.stream()
                .map(pet -> PetDto.builder()
                        .id(pet.getId())
                        .chipId(pet.getChipId())
                        .name(pet.getName())
                        .kind(pet.getKind())
                        .visitDtoList(visitMapper.mapToVisitDtoList(pet.getVisits()))
                        .birthDate(pet.getBirthDate())
                        .chronicDisease_PetDtoList(chronicDisease_petMapper.mapToChronicDisease_PetDtoList(pet.getChronicDiseases_Pet()))
                        .sterilised(pet.isSterilised())
                        .dateOfSterilization(pet.getDateOfSterilization())
                        .aggressive(pet.isAggressive())
                        .clientId(pet.getClient().getId())
                        .build())
                .collect(Collectors.toList());
    }
}
