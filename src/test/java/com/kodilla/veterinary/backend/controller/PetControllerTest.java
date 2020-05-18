package com.kodilla.veterinary.backend.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.veterinary.LocalDateAdapter;
import com.kodilla.veterinary.backend.domain.*;
import com.kodilla.veterinary.backend.facade.FilterFacade;
import com.kodilla.veterinary.backend.mapper.PetMapper;
import com.kodilla.veterinary.backend.service.PetService;
import com.kodilla.veterinary.backend.service.PetServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
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
@WebMvcTest(value = PetController.class)
public class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilterFacade filterFacade;

    @MockBean
    private PetService petService;

    @MockBean
    private PetMapper petMapper;

    private Pet pet;
    private PetDto petDto;

    @Before
    public void init() {
        Address address=new Address("street", 1, 2, "city", "postcode");
        Client  client = Client.builder()
                .id(1L)
                .legalID("93040508806")
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("369050542")
                .address(address)
                .email("email")
                .pets(Arrays.asList(new Pet[]{}))
                .build();
//        pet = new Pet(1L, "chipId", "name", "kind", Arrays.asList(new Visit[]{}), LocalDate.now(), Arrays.asList(new ChronicDisease_Pet[]{}), true, LocalDate.now(), true, client);
//        petDto = new PetDto(1L, "chipId", "name", "kind", Arrays.asList(new VisitDto[]{}), LocalDate.now(), Arrays.asList(new ChronicDisease_PetDto[]{}), true, LocalDate.now(), true, 1L);
        pet = Pet.builder()
                .id(1L)
                .chipId("chipId")
                .name("name")
                .kind("kind")
                .visits(Arrays.asList(new Visit[]{}))
                .birthDate(LocalDate.now()).chronicDiseases_Pet(Arrays.asList(new ChronicDisease_Pet[]{}))
                .sterilised(true).dateOfSterilization(LocalDate.now())
                .aggressive(true)
                .client(client)
                .build();
        petDto = PetDto.builder()
                .id(1L)
                .chipId("chipId")
                .name("name")
                .kind("kind")
                .visitDtoList(Arrays.asList(new VisitDto[]{}))
                .birthDate(LocalDate.now())
                .chronicDisease_PetDtoList(Arrays.asList(new ChronicDisease_PetDto[]{}))
                .sterilised(true).dateOfSterilization(LocalDate.now())
                .aggressive(true)
                .clientId(1L)
                .build();
    }

    @Test
    public void shouldGetEmptyList() throws Exception {
        List<PetDto> petDtos = new ArrayList<>();
        when(petMapper.mapToPetDtoList(petService.getAllPets())).thenReturn(petDtos);

        //When & Then
        mockMvc.perform(get("/v1/pet")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void getClientsPets() throws Exception {
        List<PetDto> petDtos = new ArrayList<>();
        petDtos.add(petDto);
        when(petMapper.mapToPetDtoList(petService.getAllClientPets(1L))).thenReturn(petDtos);

        //When & Then
        mockMvc.perform(get("/v1/pet/client/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].chipId", is("chipId")))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].kind", is("kind")))
                .andExpect(jsonPath("$[0].visitDtoList", is(new ArrayList<>())))
                .andExpect(jsonPath("$[0].birthDate", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$[0].chronicDisease_PetDtoList", is(new ArrayList<>())))
                .andExpect(jsonPath("$[0].sterilised", is(true)))
                .andExpect(jsonPath("$[0].dateOfSterilization", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$[0].aggressive", is(true)))
                .andExpect(jsonPath("$[0].clientId", is(1)));

    }

    @Test
    public void getPets() throws Exception {
        List<PetDto> petDtos = new ArrayList<>();
        petDtos.add(petDto);
        when(petMapper.mapToPetDtoList(petService.getAllPets())).thenReturn(petDtos);

        //When & Then
        mockMvc.perform(get("/v1/pet")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].chipId", is("chipId")))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].kind", is("kind")))
                .andExpect(jsonPath("$[0].visitDtoList", is(new ArrayList<>())))
                .andExpect(jsonPath("$[0].birthDate", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$[0].chronicDisease_PetDtoList", is(new ArrayList<>())))
                .andExpect(jsonPath("$[0].sterilised", is(true)))
                .andExpect(jsonPath("$[0].dateOfSterilization", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$[0].aggressive", is(true)))
                .andExpect(jsonPath("$[0].clientId", is(1)));

    }

    @Test
    public void getPet() throws Exception {
        //Given

        when(petService.getPet(1L)).thenReturn(Optional.of(pet));
        when(petMapper.mapToPetDto(pet)).thenReturn(petDto);

        //When & Then
        mockMvc.perform(get("/v1/pet/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.chipId", is("chipId")))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.kind", is("kind")))
                .andExpect(jsonPath("$.visitDtoList", is(new ArrayList<>())))
                .andExpect(jsonPath("$.birthDate", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$.chronicDisease_PetDtoList", is(new ArrayList<>())))
                .andExpect(jsonPath("$.sterilised", is(true)))
                .andExpect(jsonPath("$.dateOfSterilization", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$.aggressive", is(true)))
                .andExpect(jsonPath("$.clientId", is(1)));
    }

    @Test
    public void deletePet() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/pet/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePet() throws Exception {
        //Given
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(petDto);

        when(petMapper.mapToPetDto(petService.savePet(petMapper.mapToUpdatedPet(petDto)))).thenReturn(petDto);

        //When & Then
        mockMvc.perform(put("/v1/pet").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.chipId", is("chipId")))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.kind", is("kind")))
                .andExpect(jsonPath("$.visitDtoList", is(new ArrayList<>())))
                .andExpect(jsonPath("$.birthDate", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$.chronicDisease_PetDtoList", is(new ArrayList<>())))
                .andExpect(jsonPath("$.sterilised", is(true)))
                .andExpect(jsonPath("$.dateOfSterilization", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$.aggressive", is(true)))
                .andExpect(jsonPath("$.clientId", is(1)));
    }

    @Test
    public void createPet() throws Exception {
        //Given
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(petDto);

        //When & Then
        mockMvc.perform(post("/v1/pet").contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}