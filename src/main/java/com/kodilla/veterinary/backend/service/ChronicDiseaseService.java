package com.kodilla.veterinary.backend.service;

import com.kodilla.veterinary.backend.domain.ChronicDisease;
import com.kodilla.veterinary.backend.repository.ChronicDiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChronicDiseaseService {

    @Autowired
    private ChronicDiseaseRepository repository;

    public List<ChronicDisease> getAllChronicDiseases(){
        return repository.findAll();
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

