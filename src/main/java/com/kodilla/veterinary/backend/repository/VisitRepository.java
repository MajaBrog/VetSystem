package com.kodilla.veterinary.backend.repository;
import com.kodilla.veterinary.backend.domain.Visit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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
