package com.kodilla.veterinary.service;

import com.kodilla.veterinary.domain.Pet;
import com.kodilla.veterinary.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    @Autowired
    private PetRepository repository;

    public List<Pet> getAllPets(){
        return repository.findAll();
    }

    public List<Pet> getAllClientPets(final Long clientId){
        return repository.findAll().stream()
                .filter(p->p.getClient().getId().equals(clientId))
                .collect(Collectors.toList());
    }

    public Optional<Pet> getPet(final Long id){
        return repository.findById(id);
    }

    public Pet savePet(final Pet pet){
        return repository.save(pet);
    }

    public void deletePet(final Long id){
        repository.deleteById(id);
    }
}
