package com.kodilla.veterinary.controller;

import com.kodilla.veterinary.domain.ClientDto;
import com.kodilla.veterinary.mapper.ClientMapper;
import com.kodilla.veterinary.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/Client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getClients")
    private List<ClientDto> getClients(){
        return clientMapper.mapToClientDtoList(clientService.getAllClients());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getClient")
    public ClientDto getClient(Long clientId) throws RecordNotFoundException {
        return clientMapper.mapToClientDto(clientService.getClient(clientId).orElseThrow(RecordNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteClient")
    public void deleteClient(Long clientId) {
        clientService.deleteClient(clientId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateClient")
    public ClientDto updateClient(@RequestBody ClientDto clientDto) {
        return clientMapper.mapToClientDto(clientService.saveClient(clientMapper.mapToClient(clientDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createClient", consumes = APPLICATION_JSON_VALUE)
    public void createClient(@RequestBody ClientDto clientDto) {
        clientService.saveClient(clientMapper.mapToClient(clientDto));
    }
}
