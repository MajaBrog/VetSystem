package com.kodilla.veterinary.backend.controller;

import com.kodilla.veterinary.backend.domain.ClientDto;
import com.kodilla.veterinary.backend.facade.FilterFacade;
import com.kodilla.veterinary.backend.facade.SearchException;
import com.kodilla.veterinary.backend.mapper.ClientMapper;
import com.kodilla.veterinary.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private FilterFacade filterFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/client")
    private List<ClientDto> getClients() {
        return clientMapper.mapToClientDtoList(clientService.getAllClients());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client/{clientId}")
    public ClientDto getClient(@PathVariable Long clientId) throws RecordNotFoundException {
        return clientMapper.mapToClientDto(clientService.getClient(clientId).orElseThrow(RecordNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client/filter/{nameFragment}")
    public List<ClientDto> filterClientByLastName(@PathVariable String nameFragment) throws SearchException {
        return clientMapper.mapToClientDtoList(filterFacade.filterClients(nameFragment));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/client/{clientId}")
    public void deleteClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/client")
    public void updateClient(@RequestBody ClientDto clientDto) {
        clientService.saveClient(clientMapper.mapToUpdatedClient(clientDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/client", consumes = APPLICATION_JSON_VALUE)
    public void createClient(@RequestBody ClientDto clientDto) {
        clientService.saveClient(clientMapper.mapToClient(clientDto));
    }
}
