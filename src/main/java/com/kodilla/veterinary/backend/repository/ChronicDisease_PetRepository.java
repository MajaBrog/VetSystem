package com.kodilla.veterinary.backend.repository;

import com.kodilla.veterinary.backend.domain.ChronicDisease_Pet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ChronicDisease_PetRepository extends CrudRepository<ChronicDisease_Pet, Long> {
    @Override
    List<ChronicDisease_Pet> findAll();

    @Override
    Optional<ChronicDisease_Pet> findById(Long id);

    @Override
    ChronicDisease_Pet save(ChronicDisease_Pet chronicDisease_Pet);

    @Override
    void deleteById(Long id);
}