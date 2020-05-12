package com.kodilla.veterinary.backend.controller;

import com.kodilla.veterinary.backend.domain.ChronicDiseaseDto;
import com.kodilla.veterinary.backend.domain.ChronicDisease_PetDto;
import com.kodilla.veterinary.backend.mapper.ChronicDiseaseMapper;
import com.kodilla.veterinary.backend.mapper.ChronicDisease_PetMapper;
import com.kodilla.veterinary.backend.service.ChronicDiseaseService;
import com.kodilla.veterinary.backend.service.ChronicDisease_PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class ChronicDiseaseController {
    @Autowired
    private ChronicDiseaseService chronicDiseaseService;

    @Autowired
    private ChronicDiseaseMapper chronicDiseaseMapper;

    @Autowired
    private ChronicDisease_PetService chronicDisease_petService;

    @Autowired
    private ChronicDisease_PetMapper chronicDisease_petMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/chronicDisease")
    private List<ChronicDiseaseDto> getAllChronicDiseases() {
        return chronicDiseaseMapper.mapToChronicDiseaseDtoList(chronicDiseaseService.getAllChronicDiseases());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/chronicDisease/{chronicDiseaseId}")
    public ChronicDiseaseDto getChronicDisease(@PathVariable Long chronicDiseaseId) throws RecordNotFoundException {
        return chronicDiseaseMapper.mapToChronicDiseaseDto(chronicDiseaseService.getChronicDisease(chronicDiseaseId).orElseThrow(RecordNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/chronicDisease/pet/{petId}")
    private List<ChronicDisease_PetDto> getAllPetChronicDiseases(@PathVariable Long petId) {
        return chronicDisease_petMapper.mapToChronicDisease_PetDtoList(chronicDisease_petService.getAllPetChronicDiseases(petId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/chronicDisease/{chronicDiseaseId}")
    public void deleteChronicDisease(@PathVariable Long chronicDiseaseId) {
        chronicDiseaseService.deleteChronicDisease(chronicDiseaseId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/chronicDisease/pet/{chronicDisease_PetId}")
    public void deletePetChronicDisease(@PathVariable Long chronicDisease_PetId) {
        chronicDisease_petService.deleteChronicDisease_Pet(chronicDisease_PetId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/chronicDisease")
    public ChronicDiseaseDto updateChronicDisease(@RequestBody ChronicDiseaseDto chronicDiseaseDto) {
        return chronicDiseaseMapper.mapToChronicDiseaseDto(chronicDiseaseService.saveChronicDisease(chronicDiseaseMapper.mapToChronicDisease(chronicDiseaseDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/chronicDisease", consumes = APPLICATION_JSON_VALUE)
    public void createChronicDisease(@RequestBody ChronicDiseaseDto chronicDiseaseDto) {
        chronicDiseaseService.saveChronicDisease(chronicDiseaseMapper.mapToChronicDisease(chronicDiseaseDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/chronicDisease/pet", consumes = APPLICATION_JSON_VALUE)
    public void addChronicDisease(@RequestBody ChronicDisease_PetDto chronicDisease_petDto) {
        chronicDisease_petService.saveChronicDisease_Pet(chronicDisease_petMapper.mapToChronicDisease_Pet(chronicDisease_petDto));
    }


}
