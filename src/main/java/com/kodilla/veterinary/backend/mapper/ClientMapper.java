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

    public Client mapToClient(final ClientDto clientDto) {
        return Client.builder()
                .legalID(clientDto.getLegalID())
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .phoneNumber(clientDto.getPhoneNumber())
                .address(clientDto.getAddress())
                .email(clientDto.getEmail())
                .build();
    }

    public Client mapToUpdatedClient(final ClientDto clientDto) {
        return Client.builder()
                .id(clientDto.getId())
                .legalID(clientDto.getLegalID())
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .phoneNumber(clientDto.getPhoneNumber())
                .address(clientDto.getAddress())
                .email(clientDto.getEmail())
                .build();
    }

    public ClientDto mapToClientDto(final Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .legalID(client.getLegalID())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .phoneNumber(client.getPhoneNumber())
                .address(client.getAddress())
                .email(client.getEmail())
                .build();
    }

    public List<ClientDto> mapToClientDtoList(final List<Client> clientList) {
        return clientList.stream()
                .map(client -> ClientDto.builder()
                        .id(client.getId())
                        .legalID(client.getLegalID())
                        .firstName(client.getFirstName())
                        .lastName(client.getLastName())
                        .phoneNumber(client.getPhoneNumber())
                        .address(client.getAddress())
                        .email(client.getEmail())
                        .build())
                .collect(Collectors.toList());
    }
}