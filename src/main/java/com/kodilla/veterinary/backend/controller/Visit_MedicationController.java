package com.kodilla.veterinary.backend.controller;

import com.kodilla.veterinary.backend.domain.Visit_MedicationDto;
import com.kodilla.veterinary.backend.mapper.Visit_MedicationMapper;
import com.kodilla.veterinary.backend.service.Visit_MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class Visit_MedicationController {

    @Autowired
    private Visit_MedicationService visit_MedicationService;

    @Autowired
    private Visit_MedicationMapper visit_MedicationMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/visit_Medication")
    private List<Visit_MedicationDto> getVisit_Medications(){
        return visit_MedicationMapper.mapToVisit_MedicationDtoList(visit_MedicationService.getAllVisit_Medications());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/visit_Medication/{visit_MedicationId}")
    public Visit_MedicationDto getVisit_Medication(@PathVariable Long visit_MedicationId) throws RecordNotFoundException {
        return visit_MedicationMapper.mapToVisit_MedicationDto(visit_MedicationService.getVisit_Medication(visit_MedicationId).orElseThrow(RecordNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/visit_Medication/{visit_MedicationId}")
    public void deleteVisit_Medication(@PathVariable Long visit_MedicationId) {
        visit_MedicationService.deleteVisit_Medication(visit_MedicationId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/visit_Medication")
    public Visit_MedicationDto updateVisit_Medication(@RequestBody Visit_MedicationDto visit_MedicationDto) {
        return visit_MedicationMapper.mapToVisit_MedicationDto(visit_MedicationService.saveVisit_Medication(visit_MedicationMapper.mapToVisit_Medication(visit_MedicationDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/visit_Medication", consumes = APPLICATION_JSON_VALUE)
    public void addMedication(@RequestBody Visit_MedicationDto visit_MedicationDto) {
        visit_MedicationService.saveVisit_Medication(visit_MedicationMapper.mapToVisit_Medication(visit_MedicationDto));
    }
}
