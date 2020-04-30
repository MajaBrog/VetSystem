package com.kodilla.veterinary.backend.repository;

import com.kodilla.veterinary.backend.domain.Vaccination;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VaccinationRepository extends CrudRepository<Vaccination, Long> {
    @Override
    List<Vaccination> findAll();

    @Override
    Optional<Vaccination> findById(Long id);

    @Override
    Vaccination save(Vaccination vaccination);

    @Override
    void deleteById(Long id);

}
