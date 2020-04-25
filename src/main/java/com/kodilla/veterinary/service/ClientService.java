package com.kodilla.veterinary.service;


import com.kodilla.veterinary.domain.Client;
import com.kodilla.veterinary.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public List<Client> getAllClients(){
        return repository.findAll();
    }

    public Optional<Client> getClient(final Long id){
        return repository.findById(id);
    }

    public Client saveClient(final Client client){
        return repository.save(client);
    }

    public void deleteClient(final Long id){
        repository.deleteById(id);
    }
}
