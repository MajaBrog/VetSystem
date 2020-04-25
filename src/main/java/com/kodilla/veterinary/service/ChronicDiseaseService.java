package com.kodilla.veterinary.service;

import com.kodilla.veterinary.domain.ChronicDisease;
import com.kodilla.veterinary.repository.ChronicDiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChronicDiseaseService {
    @Autowired
    private ChronicDiseaseRepository repository;

    public List<ChronicDisease> getAllPetChronicDiseases(Long petId){
        return repository.findAll().stream()
                .filter(c->c.getPet().getId().equals(petId))
                .collect(Collectors.toList());
    }

    public Optional<ChronicDisease> getChronicDisease(final Long id){
        return repository.findById(id);
    }

    public ChronicDisease saveChronicDisease(final ChronicDisease chronicDisease){
        return repository.save(chronicDisease);
    }

    public void deleteChronicDisease(final Long id){
        repository.deleteById(id);
    }
}

