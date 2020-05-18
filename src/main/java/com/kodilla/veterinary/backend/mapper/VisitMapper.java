package com.kodilla.veterinary.backend.mapper;

import com.kodilla.veterinary.backend.controller.RecordNotFoundException;
import com.kodilla.veterinary.backend.domain.Visit;
import com.kodilla.veterinary.backend.domain.VisitDto;
import com.kodilla.veterinary.backend.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VisitMapper {
    @Autowired
    PetService petService;
    @Autowired
    Visit_VaccinationMapper visit_vaccinationMapper;
    @Autowired
    Visit_MedicationMapper visit_medicationMapper;

    public Visit mapToVisit(final VisitDto visitDto) {
        return new Visit(
                petService.getPet(visitDto.getPetId()).orElseThrow(RecordNotFoundException::new),
                visitDto.getDiagnose(),
                visitDto.getAdditionalRecommendation(),
                visitDto.getWeight());
    }

    public Visit mapToUpdatedVisit(final VisitDto visitDto) {
        return new Visit(
                visitDto.getId(),
                petService.getPet(visitDto.getPetId()).orElseThrow(RecordNotFoundException::new),
                visitDto.getDiagnose(),
                visitDto.getAdditionalRecommendation(),
                visitDto.getWeight());
    }

    public VisitDto mapToVisitDto(final Visit visit) {
        return new VisitDto(
                visit.getId(),
                visit.getDateOfVisit(),
                visit.getPet().getId(),
                visit.getDiagnose(),
                visit.getAdditionalRecommendation(),
                visit.getWeight(),
                visit_medicationMapper.mapToVisit_MedicationDtoList(visit.getVisit_medications()),
                visit_vaccinationMapper.mapToVisit_VaccinationDtoList(visit.getVisit_vaccinations()));
    }

    public List<VisitDto> mapToVisitDtoList(final List<Visit> visitList) {
        return visitList.stream()
                .map(v -> new VisitDto(
                        v.getId(),
                        v.getDateOfVisit(),
                        v.getPet().getId(),
                        v.getDiagnose(),
                        v.getAdditionalRecommendation(),
                        v.getWeight(),
                        visit_medicationMapper.mapToVisit_MedicationDtoList(v.getVisit_medications()),
                        visit_vaccinationMapper.mapToVisit_VaccinationDtoList(v.getVisit_vaccinations())))
                .collect(Collectors.toList());
    }
}
