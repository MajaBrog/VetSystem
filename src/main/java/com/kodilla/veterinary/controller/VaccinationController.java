package com.kodilla.veterinary.controller;
import com.kodilla.veterinary.domain.VaccinationDto;
import com.kodilla.veterinary.mapper.VaccinationMapper;
import com.kodilla.veterinary.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/vaccination")
public class VaccinationController {

    @Autowired
    private VaccinationService vaccinationService;

    @Autowired
    private VaccinationMapper vaccinationMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getVaccinations")
    private List<VaccinationDto> getVaccinations(){
        return vaccinationMapper.mapToVaccinationDtoList(vaccinationService.getAllVaccinations());
    }
    @RequestMapping(method = RequestMethod.GET, value = "getVaccination")
    public VaccinationDto getVaccination(Long vaccinationId) throws RecordNotFoundException {
        return vaccinationMapper.mapToVaccinationDto(vaccinationService.getVaccination(vaccinationId).orElseThrow(RecordNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteVaccination")
    public void deleteVaccination(Long vaccinationId) {
        vaccinationService.deleteVaccination(vaccinationId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateVaccination")
    public VaccinationDto updateVaccination(@RequestBody VaccinationDto vaccinationDto) {
        return vaccinationMapper.mapToVaccinationDto(vaccinationService.saveVaccination(vaccinationMapper.mapToVaccination(vaccinationDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createVaccination", consumes = APPLICATION_JSON_VALUE)
    public void createVaccination(@RequestBody VaccinationDto vaccinationDto) {
        vaccinationService.saveVaccination(vaccinationMapper.mapToVaccination(vaccinationDto));
    }

}