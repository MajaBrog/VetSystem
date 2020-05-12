package com.kodilla.veterinary.backend.controller;
import com.kodilla.veterinary.backend.domain.VaccinationDto;
import com.kodilla.veterinary.backend.facade.FilterFacade;
import com.kodilla.veterinary.backend.facade.SearchException;
import com.kodilla.veterinary.backend.mapper.VaccinationMapper;
import com.kodilla.veterinary.backend.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class VaccinationController {

    @Autowired
    private VaccinationService vaccinationService;

    @Autowired
    private VaccinationMapper vaccinationMapper;

    @Autowired
    private FilterFacade filterFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/vaccination")
    private List<VaccinationDto> getVaccinations(){
        return vaccinationMapper.mapToVaccinationDtoList(vaccinationService.getAllVaccinations());
    }
    @RequestMapping(method = RequestMethod.GET, value = "/vaccination/{vaccinationId}")
    public VaccinationDto getVaccination(@PathVariable Long vaccinationId) throws RecordNotFoundException {
        return vaccinationMapper.mapToVaccinationDto(vaccinationService.getVaccination(vaccinationId).orElseThrow(RecordNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/vaccination/filter/{nameFragment}")
    public  List<VaccinationDto> filterVaccinationByLastName(@PathVariable String nameFragment) throws SearchException {
        return vaccinationMapper.mapToVaccinationDtoList(filterFacade.filterVaccinations(nameFragment));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/vaccination/{vaccinationId}")
    public void deleteVaccination(@PathVariable Long vaccinationId) {
        vaccinationService.deleteVaccination(vaccinationId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/vaccination")
    public VaccinationDto updateVaccination(@RequestBody VaccinationDto vaccinationDto) {
        return vaccinationMapper.mapToVaccinationDto(vaccinationService.saveVaccination(vaccinationMapper.mapToVaccination(vaccinationDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/vaccination", consumes = APPLICATION_JSON_VALUE)
    public void createVaccination(@RequestBody VaccinationDto vaccinationDto) {
        vaccinationService.saveVaccination(vaccinationMapper.mapToVaccination(vaccinationDto));
    }

}