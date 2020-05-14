package com.kodilla.veterinary.backend.repository;

import com.kodilla.veterinary.backend.domain.Visit_Vaccination;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface Visit_VaccinationRepository extends CrudRepository<Visit_Vaccination, Long> {
    @Override
    List<Visit_Vaccination> findAll();

    @Override
    Optional<Visit_Vaccination> findById(Long id);

    @Override
    Visit_Vaccination save(Visit_Vaccination visit_Vaccination);

    @Override
    void deleteById(Long id);
}