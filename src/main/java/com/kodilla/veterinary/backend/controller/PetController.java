package com.kodilla.veterinary.backend.controller;

import com.kodilla.veterinary.backend.domain.PetDto;
import com.kodilla.veterinary.backend.mapper.PetMapper;
import com.kodilla.veterinary.backend.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private PetMapper petMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/pet")
    private List<PetDto> getAllPets() {
        return petMapper.mapToPetDtoList(petService.getAllPets());
    }


    @RequestMapping(method = RequestMethod.GET, value = "/pet/client/{clientId}")
    private List<PetDto> getClientPets(@PathVariable Long clientId) {
        return petMapper.mapToPetDtoList(petService.getAllClientPets(clientId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pet/{petId}")
    public PetDto getPet(@PathVariable Long petId) throws RecordNotFoundException {
        return petMapper.mapToPetDto(petService.getPet(petId).orElseThrow(RecordNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/pet/{petId}")
    public void deletePet(@PathVariable Long petId) {
        petService.deletePet(petId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/pet")
    public void updatePet(@RequestBody PetDto petDto) {
        petService.savePet(petMapper.mapToUpdatedPet(petDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/pet", consumes = APPLICATION_JSON_VALUE)
    public void createPet(@RequestBody PetDto petDto) {
        petService.savePet(petMapper.mapToPet(petDto));
    }


}