package com.kodilla.veterinary.controller;
import com.kodilla.veterinary.domain.VisitDto;
import com.kodilla.veterinary.mapper.VisitMapper;
import com.kodilla.veterinary.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/visit")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @Autowired
    private VisitMapper visitMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getVisits")
    private List<VisitDto> getVisits(){
        return visitMapper.mapToVisitDtoList(visitService.getAllVisits());
    }
    @RequestMapping(method = RequestMethod.GET, value = "getVisit")
    public VisitDto getVisit(Long visitId) throws RecordNotFoundException {
        return visitMapper.mapToVisitDto(visitService.getVisit(visitId).orElseThrow(RecordNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteVisit")
    public void deleteVisit(Long visitId) {
        visitService.deleteVisit(visitId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateVisit")
    public VisitDto updateVisit(@RequestBody VisitDto visitDto) {
        return visitMapper.mapToVisitDto(visitService.saveVisit(visitMapper.mapToVisit(visitDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createVisit", consumes = APPLICATION_JSON_VALUE)
    public void createVisit(@RequestBody VisitDto visitDto) {
        visitService.saveVisit(visitMapper.mapToVisit(visitDto));
    }

}