package com.kodilla.veterinary.backend.mapper;

import com.kodilla.veterinary.backend.domain.Medication;
import com.kodilla.veterinary.backend.domain.MedicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicationMapper {
    @Autowired
    Visit_MedicationMapper visit_medicationMapper;
    public Medication mapToMedication(final MedicationDto medicationDto) {
        return new Medication(
                medicationDto.getMedicationName(),
                medicationDto.getDosePerKg(),
                medicationDto.getUnit());
    }
    public Medication mapToUpdatedMedication(final MedicationDto medicationDto) {
        return new Medication(
                medicationDto.getId(),
                medicationDto.getMedicationName(),
                medicationDto.getDosePerKg(),
                medicationDto.getUnit());
    }

    public MedicationDto mapToMedicationDto(final Medication medication) {
        return new MedicationDto(
                medication.getId(),
                medication.getMedicationName(),
                medication.getDosePerKg(),
                medication.getUnit(),
                visit_medicationMapper.mapToVisit_MedicationDtoList(medication.getVisit_medications()));
    }

    public List<MedicationDto> mapToMedicationDtoList(final List<Medication> medicationList) {
        return medicationList.stream()
                .map(m -> new MedicationDto(m.getId(),m.getMedicationName(),m.getDosePerKg(),m.getUnit(),visit_medicationMapper.mapToVisit_MedicationDtoList(m.getVisit_medications())))
                .collect(Collectors.toList());
    }
}