package com.kodilla.veterinary.mapper;

import com.kodilla.veterinary.controller.RecordNotFoundException;
import com.kodilla.veterinary.domain.Visit;
import com.kodilla.veterinary.domain.VisitDto;
import com.kodilla.veterinary.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VisitMapper {
    @Autowired
    PetService petService;

    public Visit mapToVisit(final VisitDto visitDto) {
        return new Visit(
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
                visit.getWeight());
    }

    public List<VisitDto> mapToVisitDtoList(final List<Visit> visitList) {
        return visitList.stream()
                .map(v -> new VisitDto(
                        v.getId(),
                        v.getDateOfVisit(),
                        v.getPet().getId(),
                        v.getDiagnose(),
                        v.getAdditionalRecommendation(),
                        v.getWeight()))
                .collect(Collectors.toList());
    }
}
