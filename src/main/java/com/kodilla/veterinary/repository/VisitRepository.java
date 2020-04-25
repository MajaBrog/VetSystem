package com.kodilla.veterinary.repository;
import com.kodilla.veterinary.domain.Visit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VisitRepository extends CrudRepository<Visit, Long> {
    @Override
    List<Visit> findAll();

    @Override
    Optional<Visit> findById(Long id);

    @Override
    Visit save(Visit visit);

    @Override
    void deleteById(Long id);

}
