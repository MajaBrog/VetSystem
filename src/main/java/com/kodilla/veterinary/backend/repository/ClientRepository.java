package com.kodilla.veterinary.backend.repository;

import com.kodilla.veterinary.backend.domain.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    @Override
    List<Client> findAll();

    @Override
    Optional<Client> findById(Long id);

    @Override
    Client save(Client client);

    @Override
    void deleteById(Long id);

    @Query(nativeQuery = true)
    List<Client> filterClients(@Param("KEYWORD") String nameFragment);

}
