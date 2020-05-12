package com.kodilla.veterinary.backend.repository;

import com.kodilla.veterinary.backend.domain.Visit_Medication;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface Visit_MedicationRepository extends CrudRepository<Visit_Medication, Long> {
//    @EntityGraph(attributePaths = {"visit","medication"})
    @Override
    List<Visit_Medication> findAll();

    @Override
    Optional<Visit_Medication> findById(Long id);

    @Override
    Visit_Medication save(Visit_Medication visit_Medication);

    @Override
    void deleteById(Long id);
}