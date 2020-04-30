package com.kodilla.veterinary.backend.service;


import com.kodilla.veterinary.backend.domain.Visit_Vaccination;
import com.kodilla.veterinary.backend.repository.Visit_VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Visit_VaccinationService {
    @Autowired
    private Visit_VaccinationRepository repository;

    public List<Visit_Vaccination> getAllVisit_Vaccinations(){
        return repository.findAll();
    }

    public Optional<Visit_Vaccination> getVisit_Vaccination(final Long id){
        return repository.findById(id);
    }

    public Visit_Vaccination saveVisit_Vaccination(final Visit_Vaccination visit_Vaccination){
        return repository.save(visit_Vaccination);
    }

    public void deleteVisit_Vaccination(final Long id){
        repository.deleteById(id);
    }
}
