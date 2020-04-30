package com.kodilla.veterinary.backend.service;
import com.kodilla.veterinary.backend.domain.Vaccination;
import com.kodilla.veterinary.backend.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccinationService {
    @Autowired
    private VaccinationRepository repository;

    public List<Vaccination> getAllVaccinations(){
        return repository.findAll();
    }

    public Optional<Vaccination> getVaccination(final Long id){
        return repository.findById(id);
    }

    public Vaccination saveVaccination(final Vaccination vaccination){
        return repository.save(vaccination);
    }

    public void deleteVaccination(final Long id){
        repository.deleteById(id);
    }
}
