package com.kodilla.veterinary.backend.service;


import com.kodilla.veterinary.backend.domain.Client;
import com.kodilla.veterinary.backend.repository.ClientRepository;
import com.kodilla.veterinary.backend.sms.domain.SMSDto;
import com.kodilla.veterinary.backend.sms.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;
    @Autowired
    SMSService smsService;

    public List<Client> getAllClients(){
        return repository.findAll();
    }

    public Optional<Client> getClient(final Long id){
        return repository.findById(id);
    }

    public List<Client> getClientByLastName(final String lastName){
        return repository.findAll().stream()
        .filter(n->n.getLastName().contains(lastName))
        .collect(Collectors.toList());
    }

    public Client saveClient(final Client client){
        return repository.save(client);
    }

    public Client createClient(final Client client){
         ofNullable(client).ifPresent(i -> smsService.send(new SMSDto("48692080875", "VetClinic",
                "Thank you for choosing your clinic!")));
        return saveClient(client);
    }

    public void deleteClient(final Long id){
        repository.deleteById(id);
    }
}
