package com.kodilla.veterinary.service;

import com.kodilla.veterinary.domain.Medication;
import com.kodilla.veterinary.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {
    @Autowired
    private MedicationRepository repository;

    public List<Medication> getAllMedications(){
        return repository.findAll();
    }

    public Optional<Medication> getMedication(final Long id){
        return repository.findById(id);
    }

    public Medication saveMedication(final Medication medication){
        return repository.save(medication);
    }

    public void deleteMedication(final Long id){
        repository.deleteById(id);
    }
}

