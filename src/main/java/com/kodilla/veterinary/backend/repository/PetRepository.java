package com.kodilla.veterinary.backend.repository;

import com.kodilla.veterinary.backend.domain.Pet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends CrudRepository<Pet, Long> {
    @Override
    List<Pet> findAll();

    @Override
    Optional<Pet> findById(Long id);

    @Override
    Pet save(Pet pet);

    @Override
    void deleteById(Long id);

}