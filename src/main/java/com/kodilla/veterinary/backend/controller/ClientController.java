package com.kodilla.veterinary.backend.controller;

import com.kodilla.veterinary.backend.domain.ClientDto;
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

    @RequestMapping(method = RequestMethod.GET, value = "/client")
    private List<ClientDto> getClients(){
        return clientMapper.mapToClientDtoList(clientService.getAllClients());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client/{clientId}")
    public ClientDto getClient(@PathVariable Long clientId) throws RecordNotFoundException {
        return clientMapper.mapToClientDto(clientService.getClient(clientId).orElseThrow(RecordNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/client/{clientId}")
    public void deleteClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/client")
    public ClientDto updateClient(@RequestBody ClientDto clientDto) {
        return clientMapper.mapToClientDto(clientService.saveClient(clientMapper.mapToClient(clientDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/client", consumes = APPLICATION_JSON_VALUE)
    public void createClient(@RequestBody ClientDto clientDto) {
        clientService.saveClient(clientMapper.mapToClient(clientDto));
    }
}
