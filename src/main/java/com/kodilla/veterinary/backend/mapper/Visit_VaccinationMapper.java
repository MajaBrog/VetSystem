package com.kodilla.veterinary.backend.mapper;
import com.kodilla.veterinary.backend.controller.RecordNotFoundException;
import com.kodilla.veterinary.backend.domain.Visit_Vaccination;
import com.kodilla.veterinary.backend.domain.Visit_VaccinationDto;
import com.kodilla.veterinary.backend.service.VaccinationService;
import com.kodilla.veterinary.backend.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Visit_VaccinationMapper {
    @Autowired
    VisitService visitService;
    @Autowired
    VaccinationService vaccinationService;

    public Visit_Vaccination mapToVisit_Vaccination(final Visit_VaccinationDto visit_VaccinationDto){
        return new Visit_Vaccination(
                visitService.getVisit(visit_VaccinationDto.getVisitId()).orElseThrow(RecordNotFoundException::new),
                vaccinationService.getVaccination(visit_VaccinationDto.getVaccinationId()).orElseThrow(RecordNotFoundException::new),
                visit_VaccinationDto.getDose(),
                visit_VaccinationDto.getUnit());
    }

    public Visit_VaccinationDto mapToVisit_VaccinationDto(final Visit_Vaccination visit_Vaccination) {
        return new Visit_VaccinationDto(
                visit_Vaccination.getId(),
                visit_Vaccination.getVisit().getId(),
                visit_Vaccination.getVaccination().getId(),
                visit_Vaccination.getDose(),
                visit_Vaccination.getUnit(),
                visit_Vaccination.getRemindDate());
    }

    public List<Visit_VaccinationDto> mapToVisit_VaccinationDtoList(final List<Visit_Vaccination> visit_VaccinationList){
        return visit_VaccinationList.stream()
                .map(v->new Visit_VaccinationDto(
                        v.getId(),
                        v.getVisit().getId(),
                        v.getVaccination().getId(),
                        v.getDose(),
                        v.getUnit(),
                        v.getRemindDate()))
                .collect(Collectors.toList());
    }
}