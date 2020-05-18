package com.kodilla.veterinary.backend.service;

import com.kodilla.veterinary.backend.convert.client.ConvertClient;
import com.kodilla.veterinary.backend.domain.Address;
import com.kodilla.veterinary.backend.domain.Client;
import com.kodilla.veterinary.backend.domain.Pet;
import com.kodilla.veterinary.backend.repository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {
    @InjectMocks

    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ConvertClient convertClient;

    private Address address = new Address("street", 1, 2, "city", "postcode");

    @Test
    public void getAllClients() {
        //Given
        Client client1 = Client.builder()
                .id(1L)
                .legalID("93040508806")
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("369050542")
                .address(address)
                .email("email")
                .pets(Arrays.asList(new Pet[]{}))
                .build();
        Client client2 = Client.builder()
                .id(2L)
                .legalID("93040508806")
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("369050542")
                .address(address)
                .email("email")
                .pets(Arrays.asList(new Pet[]{}))
                .build();
        List<Client> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);

        when(clientRepository.findAll()).thenReturn(clients);
        //When
        List allClients = clientService.getAllClients();
        //Then
        assertEquals(2, allClients.size());
    }


    @Test
    public void getClient() {
        //Given
        Client client1 = Client.builder()
                .id(1L)
                .legalID("93040508806")
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("369050542")
                .address(address)
                .email("email")
                .pets(Arrays.asList(new Pet[]{}))
                .build();

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client1));
        //When
        Client client = clientService.getClient(1L).get();
        //Then
        assertEquals(1L, client.getId(), 0);
        assertEquals("93040508806", client.getLegalID());
        assertEquals("firstName", client.getFirstName());
        assertEquals("lastName", client.getLastName());
        assertEquals("369050542", client.getPhoneNumber());
        assertEquals(address, client.getAddress());
        assertEquals("email", client.getEmail());
        assertEquals(new ArrayList<>(), client.getPets());
    }

    @Test
    public void saveClient() {
        //Given
        Client client1 = Client.builder()
                .id(1L)
                .legalID("93040508806")
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("369050542")
                .address(address)
                .email("email")
                .pets(Arrays.asList(new Pet[]{}))
                .build();
        when(clientRepository.save(client1)).thenReturn(client1);
        //When
        Client client = clientService.saveClient(client1);
        //Then
        assertEquals(1L, client.getId(), 0);
        assertEquals("93040508806", client.getLegalID());
        assertEquals("firstName", client.getFirstName());
        assertEquals("lastName", client.getLastName());
        assertEquals("369050542", client.getPhoneNumber());
        assertEquals(address, client.getAddress());
        assertEquals("email", client.getEmail());
        assertEquals(new ArrayList<>(), client.getPets());
    }


    @Test
    public void deleteClient() {

        //When
        clientService.deleteClient(1L);
        //Then
        Mockito.verify(clientRepository, times(1)).deleteById(1L);
    }
}