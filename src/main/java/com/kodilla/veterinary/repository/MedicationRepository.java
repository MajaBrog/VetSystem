package com.kodilla.veterinary.repository;

import com.kodilla.veterinary.domain.Medication;
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