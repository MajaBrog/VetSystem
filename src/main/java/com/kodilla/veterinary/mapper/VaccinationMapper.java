package com.kodilla.veterinary.mapper;

import com.kodilla.veterinary.domain.Vaccination;
import com.kodilla.veterinary.domain.VaccinationDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VaccinationMapper {
    public Vaccination mapToVaccination(final VaccinationDto vaccinationDto) {
        return new Vaccination(
                vaccinationDto.getName(),
                vaccinationDto.getDiseases(),
                vaccinationDto.getDosePerKg(),
                vaccinationDto.getUnit(),
                vaccinationDto.isMandatory());
    }

    public VaccinationDto mapToVaccinationDto(final Vaccination vaccination) {
        return new VaccinationDto(
                vaccination.getId(),
                vaccination.getName(),
                vaccination.getDiseases(),
                vaccination.getDosePerKg(),
                vaccination.getUnit(),
                vaccination.isMandatory());
    }

    public List<VaccinationDto> mapToVaccinationDtoList(final List<Vaccination> vaccinationList) {
        return vaccinationList.stream()
                .map(v -> new VaccinationDto(
                        v.getId(),
                        v.getName(),
                        v.getDiseases(),
                        v.getDosePerKg(),
                        v.getUnit(),
                        v.isMandatory()))
                        .collect(Collectors.toList());
    }
}