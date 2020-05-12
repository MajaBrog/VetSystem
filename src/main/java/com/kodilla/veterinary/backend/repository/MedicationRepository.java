package com.kodilla.veterinary.backend.repository;

import com.kodilla.veterinary.backend.domain.Medication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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

    @Query(nativeQuery = true)
    List<Medication> filterMedications(@Param("KEYWORD") String nameFragment);
}