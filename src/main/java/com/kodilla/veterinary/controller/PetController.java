package com.kodilla.veterinary.controller;
import com.kodilla.veterinary.domain.PetDto;
import com.kodilla.veterinary.mapper.PetMapper;
import com.kodilla.veterinary.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private PetMapper petMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getPets")
    private List<PetDto> getAllPets(){
        return petMapper.mapToPetDtoList(petService.getAllPets());
    }


    @RequestMapping(method = RequestMethod.GET, value = "getClientPets")
    private List<PetDto> getClientPets(Long clientId){
        return petMapper.mapToPetDtoList(petService.getAllClientPets(clientId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPet")
    public PetDto getPet(Long petId) throws RecordNotFoundException {
        return petMapper.mapToPetDto(petService.getPet(petId).orElseThrow(RecordNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deletePet")
    public void deletePet(Long petId) {
        petService.deletePet(petId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updatePet")
    public PetDto updatePet(@RequestBody PetDto petDto) {
        return petMapper.mapToPetDto(petService.savePet(petMapper.mapToPet(petDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createPet", consumes = APPLICATION_JSON_VALUE)
    public void createPet(@RequestBody PetDto petDto) {
        petService.savePet(petMapper.mapToPet(petDto));
    }


}