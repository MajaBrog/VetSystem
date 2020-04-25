package com.kodilla.veterinary.mapper;

import com.kodilla.veterinary.domain.Medication;
import com.kodilla.veterinary.domain.MedicationDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicationMapper {
    public Medication mapToMedication(final MedicationDto medicationDto) {
        return new Medication(
                medicationDto.getMedicationName(),
                medicationDto.getDosePerKg(),
                medicationDto.getUnit());
    }

    public MedicationDto mapToMedicationDto(final Medication medication) {
        return new MedicationDto(
                medication.getId(),
                medication.getMedicationName(),
                medication.getDosePerKg(),
                medication.getUnit());
    }

    public List<MedicationDto> mapToMedicationDtoList(final List<Medication> medicationList) {
        return medicationList.stream()
                .map(m -> new MedicationDto(m.getId(),m.getMedicationName(),m.getDosePerKg(),m.getUnit()))
                .collect(Collectors.toList());
    }
}