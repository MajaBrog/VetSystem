package com.kodilla.veterinary.backend.mapper;

import com.kodilla.veterinary.backend.domain.Vaccination;
import com.kodilla.veterinary.backend.domain.VaccinationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VaccinationMapper {
    @Autowired
    Visit_VaccinationMapper visit_vaccinationMapper;
    public Vaccination mapToVaccination(final VaccinationDto vaccinationDto) {
        return new Vaccination(
                vaccinationDto.getName(),
                vaccinationDto.getDisease(),
                vaccinationDto.getDosePerKg(),
                vaccinationDto.getUnit(),
                vaccinationDto.isMandatory(),
                vaccinationDto.getIntervalInWeeks());
    }

    public Vaccination mapToUpdatedVaccination(final VaccinationDto vaccinationDto) {
        return new Vaccination(
                vaccinationDto.getId(),
                vaccinationDto.getName(),
                vaccinationDto.getDisease(),
                vaccinationDto.getDosePerKg(),
                vaccinationDto.getUnit(),
                vaccinationDto.isMandatory(),
                vaccinationDto.getIntervalInWeeks());
    }


    public VaccinationDto mapToVaccinationDto(final Vaccination vaccination) {
        return new VaccinationDto(
                vaccination.getId(),
                vaccination.getName(),
                vaccination.getDisease(),
                vaccination.getDosePerKg(),
                vaccination.getUnit(),
                vaccination.isMandatory(),
                vaccination.getIntervalInWeeks(),
                visit_vaccinationMapper.mapToVisit_VaccinationDtoList(vaccination.getVisit_vaccinations()));
    }

    public List<VaccinationDto> mapToVaccinationDtoList(final List<Vaccination> vaccinationList) {
        return vaccinationList.stream()
                .map(v -> new VaccinationDto(
                        v.getId(),
                        v.getName(),
                        v.getDisease(),
                        v.getDosePerKg(),
                        v.getUnit(),
                        v.isMandatory(),
                        v.getIntervalInWeeks(),
                        visit_vaccinationMapper.mapToVisit_VaccinationDtoList(v.getVisit_vaccinations())))
                        .collect(Collectors.toList());
    }
}