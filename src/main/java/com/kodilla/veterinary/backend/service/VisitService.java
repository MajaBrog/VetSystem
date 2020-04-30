package com.kodilla.veterinary.backend.service;

import com.kodilla.veterinary.backend.domain.Visit;
import com.kodilla.veterinary.backend.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {
    @Autowired
    private VisitRepository repository;

    public List<Visit> getAllVisits(){
        return repository.findAll();
    }

    public Optional<Visit> getVisit(final Long id){
        return repository.findById(id);
    }

    public Visit saveVisit(final Visit visit){
        return repository.save(visit);
    }

    public void deleteVisit(final Long id){
        repository.deleteById(id);
    }
}

