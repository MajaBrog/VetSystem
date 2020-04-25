package com.kodilla.veterinary.mapper;

import com.kodilla.veterinary.controller.RecordNotFoundException;
import com.kodilla.veterinary.domain.Pet;
import com.kodilla.veterinary.domain.PetDto;
import com.kodilla.veterinary.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetMapper {

    @Autowired
    ClientService clientService;

    public Pet mapToPet(final PetDto petDto){
        return new Pet(
                petDto.getChipId(),
                petDto.getName(),
                petDto.getKind(),
                petDto.getBirthday(),
                petDto.isSterilised(),
                petDto.getDateOfSterilization(),
                petDto.isAggressive(),
                clientService.getClient(petDto.getClientId()).orElseThrow(RecordNotFoundException::new));
    }

    public PetDto mapToPetDto(final Pet pet) {
        return new PetDto(
                pet.getId(),
                pet.getChipId(),
                pet.getName(),
                pet.getKind(),
                pet.getBirthday(),
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
                        p.getBirthday(),
                        p.isSterilised(),
                        p.getDateOfSterilization(),
                        p.isAggressive(),
                        p.getClient().getId()))
                .collect(Collectors.toList());
    }
}
