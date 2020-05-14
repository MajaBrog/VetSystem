package com.kodilla.veterinary.backend.repository;

import com.kodilla.veterinary.backend.domain.ChronicDisease;
import com.kodilla.veterinary.backend.domain.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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

    @Query(nativeQuery = true)
    List<ChronicDisease> filterChronicDiseases(@Param("KEYWORD") String nameFragment);
}