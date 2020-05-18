package com.kodilla.veterinary.backend.service;


import com.kodilla.veterinary.backend.domain.ChronicDisease_Pet;
import com.kodilla.veterinary.backend.repository.ChronicDisease_PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChronicDisease_PetService {
    @Autowired
    private ChronicDisease_PetRepository repository;

    public List<ChronicDisease_Pet> getAllPetChronicDiseases(Long petId){
        return repository.findAll().stream()
                .filter(c->c.getPet().getId().equals(petId))
                .collect(Collectors.toList());
    }
//    public List<ChronicDisease_Pet> getAllPetWithChronicDiseases(Long chronicDiseaseId){
//        return repository.findAll().stream()
//                .filter(c->c.getChronicDisease().getId().equals(chronicDiseaseId))
//                .collect(Collectors.toList());
//    }
//    public Optional<ChronicDisease_Pet> getChronicDisease_Pet(final Long id){
//        return repository.findById(id);
//    }

    public ChronicDisease_Pet saveChronicDisease_Pet(final ChronicDisease_Pet chronicDisease_Pet){
        return repository.save(chronicDisease_Pet);
    }

    public void deleteChronicDisease_Pet(final Long id){
        repository.deleteById(id);
    }
}
