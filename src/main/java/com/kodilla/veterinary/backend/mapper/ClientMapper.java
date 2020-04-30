package com.kodilla.veterinary.backend.mapper;
import com.kodilla.veterinary.backend.domain.Client;
import com.kodilla.veterinary.backend.domain.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {
    @Autowired
    PetMapper petMapper;
    public Client mapToClient(final ClientDto clientDto){
        return new Client(
                clientDto.getLegalID(),
                clientDto.getFirstName(),
                clientDto.getLastName(),
                clientDto.getPhoneNumber(),
                clientDto.getAddress(),
                clientDto.getEmail());
    }

    public ClientDto mapToClientDto(final Client client) {
        return new ClientDto(
                client.getId(),
                client.getLegalID(),
                client.getFirstName(),
                client.getLastName(),
                client.getPhoneNumber(),
                client.getAddress(),
                client.getEmail(),
                petMapper.mapToPetDtoList(client.getPets()));
    }

    public List<ClientDto> mapToClientDtoList(final List<Client> clientList){
        return clientList.stream()
                .map(c->new ClientDto(
                        c.getId(),
                        c.getLegalID(),
                        c.getFirstName(),
                        c.getLastName(),
                        c.getPhoneNumber(),
                        c.getAddress(),
                        c.getEmail(),
                        petMapper.mapToPetDtoList(c.getPets())))
                .collect(Collectors.toList());
    }
}