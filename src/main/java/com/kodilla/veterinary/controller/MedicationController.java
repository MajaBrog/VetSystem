package com.kodilla.veterinary.controller;

import com.kodilla.veterinary.domain.MedicationDto;
import com.kodilla.veterinary.mapper.MedicationMapper;
import com.kodilla.veterinary.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/medication")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private MedicationMapper medicationMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getMedications")
    private List<MedicationDto> getMedications(){
        return medicationMapper.mapToMedicationDtoList(medicationService.getAllMedications());
    }
    @RequestMapping(method = RequestMethod.GET, value = "getMedication")
    public MedicationDto getMedication(Long medicationId) throws RecordNotFoundException {
        return medicationMapper.mapToMedicationDto(medicationService.getMedication(medicationId).orElseThrow(RecordNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteMedication")
    public void deleteMedication(Long medicationId) {
        medicationService.deleteMedication(medicationId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateMedication")
    public MedicationDto updateMedication(@RequestBody MedicationDto medicationDto) {
        return medicationMapper.mapToMedicationDto(medicationService.saveMedication(medicationMapper.mapToMedication(medicationDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createMedication", consumes = APPLICATION_JSON_VALUE)
    public void createMedication(@RequestBody MedicationDto medicationDto) {
        medicationService.saveMedication(medicationMapper.mapToMedication(medicationDto));
    }

}