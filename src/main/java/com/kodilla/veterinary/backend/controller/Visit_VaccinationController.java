package com.kodilla.veterinary.backend.controller;

import com.kodilla.veterinary.backend.domain.Visit_VaccinationDto;
import com.kodilla.veterinary.backend.mapper.Visit_VaccinationMapper;
import com.kodilla.veterinary.backend.service.Visit_VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class Visit_VaccinationController {

    @Autowired
    private Visit_VaccinationService visit_VaccinationService;

    @Autowired
    private Visit_VaccinationMapper visit_VaccinationMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/visit_Vaccination")
    private List<Visit_VaccinationDto> getVisit_Vaccinations() {
        return visit_VaccinationMapper.mapToVisit_VaccinationDtoList(visit_VaccinationService.getAllVisit_Vaccinations());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/visit_Vaccination/{visit_VaccinationId}")
    public Visit_VaccinationDto getVisit_Vaccination(@PathVariable Long visit_VaccinationId) throws RecordNotFoundException {
        return visit_VaccinationMapper.mapToVisit_VaccinationDto(visit_VaccinationService.getVisit_Vaccination(visit_VaccinationId).orElseThrow(RecordNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/visit_Vaccination/visit/{visitId}")
    private List<Visit_VaccinationDto> getVisitMedications(@PathVariable Long visitId) {
        return visit_VaccinationMapper.mapToVisit_VaccinationDtoList(visit_VaccinationService.getVisitVaccinations(visitId));
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/visit_Vaccination/{visit_VaccinationId}")
    public void deleteVisit_Vaccination(@PathVariable Long visit_VaccinationId) {
        visit_VaccinationService.deleteVisit_Vaccination(visit_VaccinationId);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/visit_Vaccination", consumes = APPLICATION_JSON_VALUE)
    public void addVaccination(@RequestBody Visit_VaccinationDto visit_VaccinationDto) {
        visit_VaccinationService.saveVisit_Vaccination(visit_VaccinationMapper.mapToVisit_Vaccination(visit_VaccinationDto));
    }
}
