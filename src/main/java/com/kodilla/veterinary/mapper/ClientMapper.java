package com.kodilla.veterinary.mapper;
import com.kodilla.veterinary.domain.Client;
import com.kodilla.veterinary.domain.ClientDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {
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
                client.getEmail());
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
                        c.getEmail()))
                .collect(Collectors.toList());
    }
}