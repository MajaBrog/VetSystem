package com.kodilla.veterinary.backend.controller;

import com.google.gson.Gson;
import com.kodilla.veterinary.backend.domain.*;
import com.kodilla.veterinary.backend.facade.FilterFacade;
import com.kodilla.veterinary.backend.mapper.ClientMapper;
import com.kodilla.veterinary.backend.service.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ClientController.class)
public class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FilterFacade filterFacade;

    @MockBean
    private ClientService clientService;

    @MockBean
    private ClientMapper clientMapper;

    private Client client;
    private ClientDto clientDto;

    @Before
    public void init(){
        Address address=new Address("street", 1, 2, "city", "postcode");
         client = Client.builder()
                .id(1L)
                .legalID("93040508806")
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("369050542")
                .address(address)
                .email("email")
                .pets(Arrays.asList(new Pet[]{}))
                .build();

        clientDto = ClientDto.builder()
                .id(1L)
                .legalID("93040508806")
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("369050542")
                .address(address)
                .email("email")
                .petDtoList(Arrays.asList(new PetDto[]{}))
                .build();
    }

    @Test
    public void shouldGetEmptyList() throws Exception {
        List<ClientDto> clientDtos = new ArrayList<>();
        when(clientMapper.mapToClientDtoList(clientService.getAllClients())).thenReturn(clientDtos);

        //When & Then
        mockMvc.perform(get("/v1/client")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void getClients() throws Exception {
        List<ClientDto> clientDtos = new ArrayList<>();
        clientDtos.add(clientDto);
        when(clientMapper.mapToClientDtoList(clientService.getAllClients())).thenReturn(clientDtos);

        //When & Then
        mockMvc.perform(get("/v1/client")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].legalID", is("93040508806")))
                .andExpect(jsonPath("$[0].firstName", is("firstName")))
                .andExpect(jsonPath("$[0].lastName", is("lastName")))
                .andExpect(jsonPath("$[0].phoneNumber", is("369050542")))
                .andExpect(jsonPath("$[0].address.street", is("street")))
                .andExpect(jsonPath("$[0].address.houseNumber", is(1)))
                .andExpect(jsonPath("$[0].address.homeNumber", is(2)))
                .andExpect(jsonPath("$[0].address.city", is("city")))
                .andExpect(jsonPath("$[0].address.postcode", is("postcode")))
                .andExpect(jsonPath("$[0].email", is("email")))
                .andExpect(jsonPath("$[0].petDtoList", is(new ArrayList<>())));
    }
    @Test
    public void filterClientByLastName() throws Exception {
        List<ClientDto> clientDtos = new ArrayList<>();
        clientDtos.add(clientDto);
        when(clientMapper.mapToClientDtoList(filterFacade.filterClients("na"))).thenReturn(clientDtos);

        //When & Then
        mockMvc.perform(get("/v1/client/filter/na")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].legalID", is("93040508806")))
                .andExpect(jsonPath("$[0].firstName", is("firstName")))
                .andExpect(jsonPath("$[0].lastName", is("lastName")))
                .andExpect(jsonPath("$[0].phoneNumber", is("369050542")))
                .andExpect(jsonPath("$[0].address.street", is("street")))
                .andExpect(jsonPath("$[0].address.houseNumber", is(1)))
                .andExpect(jsonPath("$[0].address.homeNumber", is(2)))
                .andExpect(jsonPath("$[0].address.city", is("city")))
                .andExpect(jsonPath("$[0].address.postcode", is("postcode")))
                .andExpect(jsonPath("$[0].email", is("email")))
                .andExpect(jsonPath("$[0].petDtoList", is(new ArrayList<>())));
    }

    @Test
    public void getClient() throws Exception {
        //Given
        when(clientService.getClient(1L)).thenReturn(Optional.of(client));
        when(clientMapper.mapToClientDto(client)).thenReturn(clientDto);

        //When & Then
        mockMvc.perform(get("/v1/client/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.legalID", is("93040508806")))
                .andExpect(jsonPath("$.firstName", is("firstName")))
                .andExpect(jsonPath("$.lastName", is("lastName")))
                .andExpect(jsonPath("$.phoneNumber", is("369050542")))
                .andExpect(jsonPath("$.address.street", is("street")))
                .andExpect(jsonPath("$.address.houseNumber", is(1)))
                .andExpect(jsonPath("$.address.homeNumber", is(2)))
                .andExpect(jsonPath("$.address.city", is("city")))
                .andExpect(jsonPath("$.address.postcode", is("postcode")))
                .andExpect(jsonPath("$.email", is("email")))
                .andExpect(jsonPath("$.petDtoList", is(new ArrayList<>())));
    }
    @Test
    public void deleteClient() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/client/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateClient() throws Exception {
        //Given
        Gson gson = new Gson();
        String jsonContent = gson.toJson(clientDto);

        when(clientMapper.mapToClientDto(clientService.saveClient(clientMapper.mapToUpdatedClient(clientDto)))).thenReturn(clientDto);

        //When & Then
        mockMvc.perform(put("/v1/client").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void createClient() throws Exception {
        //Given
        Gson gson = new Gson();
        String jsonContent = gson.toJson(clientDto);

        //When & Then
        mockMvc.perform(post("/v1/client").contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}