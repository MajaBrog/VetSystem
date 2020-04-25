package com.kodilla.veterinary.repository;

import com.kodilla.veterinary.domain.Vaccination;
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
