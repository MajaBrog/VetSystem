package com.kodilla.veterinary.backend.controller;

import com.kodilla.veterinary.backend.domain.MedicationDto;
import com.kodilla.veterinary.backend.mapper.MedicationMapper;
import com.kodilla.veterinary.backend.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private MedicationMapper medicationMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/medication")
    private List<MedicationDto> getMedications(){
        return medicationMapper.mapToMedicationDtoList(medicationService.getAllMedications());
    }
    @RequestMapping(method = RequestMethod.GET, value = "/medication/{medicationId}")
    public MedicationDto getMedication(@PathVariable Long medicationId) throws RecordNotFoundException {
        return medicationMapper.mapToMedicationDto(medicationService.getMedication(medicationId).orElseThrow(RecordNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/medication/{medicationId}")
    public void deleteMedication(@PathVariable Long medicationId) {
        medicationService.deleteMedication(medicationId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/medication")
    public MedicationDto updateMedication(@RequestBody MedicationDto medicationDto) {
        return medicationMapper.mapToMedicationDto(medicationService.saveMedication(medicationMapper.mapToMedication(medicationDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/medication", consumes = APPLICATION_JSON_VALUE)
    public void createMedication(@RequestBody MedicationDto medicationDto) {
        medicationService.saveMedication(medicationMapper.mapToMedication(medicationDto));
    }

}