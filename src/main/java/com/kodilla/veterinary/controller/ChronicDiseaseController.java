package com.kodilla.veterinary.controller;

import com.kodilla.veterinary.domain.ChronicDiseaseDto;
import com.kodilla.veterinary.mapper.ChronicDiseaseMapper;
import com.kodilla.veterinary.service.ChronicDiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/chronicDisease")
public class ChronicDiseaseController {
    @Autowired
    private ChronicDiseaseService chronicDiseaseService;

    @Autowired
    private ChronicDiseaseMapper chronicDiseaseMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getPetChronicDiseases")
    private List<ChronicDiseaseDto> getAllPetChronicDiseases(Long petId) {
        return chronicDiseaseMapper.mapToChronicDiseaseDtoList(chronicDiseaseService.getAllPetChronicDiseases(petId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getChronicDisease")
    public ChronicDiseaseDto getChronicDisease(Long chronicDiseaseId) throws RecordNotFoundException {
        return chronicDiseaseMapper.mapToChronicDiseaseDto(chronicDiseaseService.getChronicDisease(chronicDiseaseId).orElseThrow(RecordNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteChronicDisease")
    public void deleteChronicDisease(Long chronicDiseaseId) {
        chronicDiseaseService.deleteChronicDisease(chronicDiseaseId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateChronicDisease")
    public ChronicDiseaseDto updateChronicDisease(@RequestBody ChronicDiseaseDto chronicDiseaseDto) {
        return chronicDiseaseMapper.mapToChronicDiseaseDto(chronicDiseaseService.saveChronicDisease(chronicDiseaseMapper.mapToChronicDisease(chronicDiseaseDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createChronicDisease", consumes = APPLICATION_JSON_VALUE)
    public void createChronicDisease(@RequestBody ChronicDiseaseDto chronicDiseaseDto) {
        chronicDiseaseService.saveChronicDisease(chronicDiseaseMapper.mapToChronicDisease(chronicDiseaseDto));
    }

}
