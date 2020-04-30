package com.kodilla.veterinary.backend.repository;

import com.kodilla.veterinary.backend.domain.ChronicDisease;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ChronicDiseaseRepository extends CrudRepository<ChronicDisease, Long> {
    @Override
    List<ChronicDisease> findAll();

    @Override
    Optional<ChronicDisease> findById(Long id);

    @Override
    ChronicDisease save(ChronicDisease chronicDisease);

    @Override
    void deleteById(Long id);

}