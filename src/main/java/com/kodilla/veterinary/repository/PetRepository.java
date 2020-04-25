package com.kodilla.veterinary.repository;

import com.kodilla.veterinary.domain.Pet;
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