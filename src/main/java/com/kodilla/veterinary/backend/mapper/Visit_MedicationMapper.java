package com.kodilla.veterinary.backend.mapper;
import com.kodilla.veterinary.backend.controller.RecordNotFoundException;
import com.kodilla.veterinary.backend.domain.Visit_Medication;
import com.kodilla.veterinary.backend.domain.Visit_MedicationDto;
import com.kodilla.veterinary.backend.service.MedicationService;
import com.kodilla.veterinary.backend.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Visit_MedicationMapper {
    @Autowired
    VisitService visitService;
    @Autowired
    MedicationService medicationService;

    public Visit_Medication mapToVisit_Medication(final Visit_MedicationDto visit_MedicationDto){
        return new Visit_Medication(
                visitService.getVisit(visit_MedicationDto.getVisitId()).orElseThrow(RecordNotFoundException::new),
                medicationService.getMedication(visit_MedicationDto.getMedicationId()).orElseThrow(RecordNotFoundException::new),
                visit_MedicationDto.getDose(),
                visit_MedicationDto.getUnit());
    }

    public Visit_MedicationDto mapToVisit_MedicationDto(final Visit_Medication visit_Medication) {
        return new Visit_MedicationDto(
                visit_Medication.getId(),
                visit_Medication.getVisit().getId(),
                visit_Medication.getMedication().getId(),
                visit_Medication.getDose(),
                visit_Medication.getUnit());
    }

    public List<Visit_MedicationDto> mapToVisit_MedicationDtoList(final List<Visit_Medication> visit_MedicationList){
        return visit_MedicationList.stream()
                .map(v->new Visit_MedicationDto(
                        v.getId(),
                        v.getVisit().getId(),
                        v.getMedication().getId(),
                        v.getDose(),
                        v.getUnit()))
                .collect(Collectors.toList());
    }
}
