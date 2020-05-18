package com.kodilla.veterinary.backend.service;


import com.kodilla.veterinary.backend.domain.Visit_Medication;
import com.kodilla.veterinary.backend.repository.Visit_MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Visit_MedicationService {
    @Autowired
    private Visit_MedicationRepository repository;

    public List<Visit_Medication> getAllVisit_Medications() {
        return repository.findAll();
    }

    public Optional<Visit_Medication> getVisit_Medication(final Long id) {
        return repository.findById(id);
    }

    public List<Visit_Medication> getVisitMedications(Long visitId) {
        return repository.findAll().stream()
                .filter(n -> n.getVisit().getId().equals(visitId))
                .collect(Collectors.toList());
    }

    public Visit_Medication saveVisit_Medication(final Visit_Medication visit_Medication) {
        return repository.save(visit_Medication);
    }

    public void deleteVisit_Medication(final Long id) {
        repository.deleteById(id);
    }
}

