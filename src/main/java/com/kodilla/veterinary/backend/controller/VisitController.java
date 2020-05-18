package com.kodilla.veterinary.backend.controller;
import com.kodilla.veterinary.backend.domain.Visit;
import com.kodilla.veterinary.backend.domain.VisitDto;
import com.kodilla.veterinary.backend.facade.FilterFacade;
import com.kodilla.veterinary.backend.facade.SearchException;
import com.kodilla.veterinary.backend.mapper.VisitMapper;
import com.kodilla.veterinary.backend.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @Autowired
    private VisitMapper visitMapper;

    @Autowired
    FilterFacade filterFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/visit")
    private List<VisitDto> getVisits(){
        return visitMapper.mapToVisitDtoList(visitService.getAllVisits());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/visit/{visitId}")
    public VisitDto getVisit(@PathVariable Long visitId) throws RecordNotFoundException {
        return visitMapper.mapToVisitDto(visitService.getVisit(visitId).orElseThrow(RecordNotFoundException::new));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/visit/filter/{nameFragment}")
    private List<VisitDto> filterVisit(@PathVariable String nameFragment) throws SearchException {
        return visitMapper.mapToVisitDtoList(filterFacade.filterVisits(nameFragment));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/visit/pet/{petId}")
    public List<VisitDto>  getPetVisits(@PathVariable Long petId) throws RecordNotFoundException {
        return visitMapper.mapToVisitDtoList(visitService.getPetVisits(petId));
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/visit/{visitId}")
    public void deleteVisit(@PathVariable Long visitId) {
        visitService.deleteVisit(visitId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/visit")
    public VisitDto updateVisit(@RequestBody VisitDto visitDto) {
        return visitMapper.mapToVisitDto(visitService.saveVisit(visitMapper.mapToUpdatedVisit(visitDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/visit", consumes = APPLICATION_JSON_VALUE)
    public VisitDto createVisit(@RequestBody VisitDto visitDto) {
        return visitMapper.mapToVisitDto(visitService.saveVisit(visitMapper.mapToVisit(visitDto)));
    }

}