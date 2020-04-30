package com.kodilla.veterinary.backend.repository;

import com.kodilla.veterinary.backend.domain.Medication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MedicationRepository extends CrudRepository<Medication, Long> {
    @Override
    List<Medication> findAll();

    @Override
    Optional<Medication> findById(Long id);

    @Override
    Medication save(Medication medication);

    @Override
    void deleteById(Long id);

}