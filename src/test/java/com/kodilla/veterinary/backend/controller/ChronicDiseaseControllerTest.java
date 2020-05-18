package com.kodilla.veterinary.backend.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.veterinary.LocalDateAdapter;
import com.kodilla.veterinary.backend.domain.ChronicDisease;
import com.kodilla.veterinary.backend.domain.ChronicDiseaseDto;
import com.kodilla.veterinary.backend.domain.ChronicDisease_Pet;
import com.kodilla.veterinary.backend.domain.ChronicDisease_PetDto;
import com.kodilla.veterinary.backend.facade.FilterFacade;
import com.kodilla.veterinary.backend.mapper.ChronicDiseaseMapper;
import com.kodilla.veterinary.backend.mapper.ChronicDisease_PetMapper;
import com.kodilla.veterinary.backend.service.ChronicDiseaseService;
import com.kodilla.veterinary.backend.service.ChronicDisease_PetService;
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
@WebMvcTest(value = ChronicDiseaseController.class)
public class ChronicDiseaseControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FilterFacade filterFacade;

    @MockBean
    private ChronicDiseaseService chronicDiseaseService;

    @MockBean
    private ChronicDiseaseMapper chronicDiseaseMapper;

    @MockBean
    private ChronicDisease_PetService chronicDisease_petService;

    @MockBean
    private ChronicDisease_PetMapper chronicDisease_petMapper;


    @Test
    public void shouldGetEmptyList() throws Exception {
        List<ChronicDiseaseDto> chronicDiseaseDtos = new ArrayList<>();
        when(chronicDiseaseMapper.mapToChronicDiseaseDtoList(chronicDiseaseService.getAllChronicDiseases())).thenReturn(chronicDiseaseDtos);

        //When & Then
        mockMvc.perform(get("/v1/chronicDisease")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void getChronicDiseases() throws Exception {
        ChronicDiseaseDto chronicDiseaseDto = new ChronicDiseaseDto(1L, "name", Arrays.asList(new ChronicDisease_PetDto[]{}));
        List<ChronicDiseaseDto> chronicDiseaseDtos = new ArrayList<>();
        chronicDiseaseDtos.add(chronicDiseaseDto);
        when(chronicDiseaseMapper.mapToChronicDiseaseDtoList(chronicDiseaseService.getAllChronicDiseases())).thenReturn(chronicDiseaseDtos);

        //When & Then
        mockMvc.perform(get("/v1/chronicDisease")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].chronicDisease_PetDtoList", is(new ArrayList<>())));
    }

    @Test
    public void filterChronicDiseaseByLastName() throws Exception {
        ChronicDiseaseDto chronicDiseaseDto = new ChronicDiseaseDto(1L, "name", Arrays.asList(new ChronicDisease_PetDto[]{}));
        List<ChronicDiseaseDto> chronicDiseaseDtos = new ArrayList<>();
        chronicDiseaseDtos.add(chronicDiseaseDto);
        when(chronicDiseaseMapper.mapToChronicDiseaseDtoList(filterFacade.filterChronicDiseases("na"))).thenReturn(chronicDiseaseDtos);

        //When & Then
        mockMvc.perform(get("/v1/chronicDisease/filter/na")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].chronicDisease_PetDtoList", is(new ArrayList<>())));
    }

    @Test
    public void getAllPetChronicDiseases() throws Exception {
        ChronicDisease_PetDto chronicDisease_petDto = new ChronicDisease_PetDto(1L, 1L, 1L, LocalDate.now());
        List<ChronicDisease_PetDto> chronicDisease_petDtos = new ArrayList<>();
        chronicDisease_petDtos.add(chronicDisease_petDto);
        when(chronicDisease_petMapper.mapToChronicDisease_PetDtoList(chronicDisease_petService.getAllPetChronicDiseases(1L))).thenReturn(chronicDisease_petDtos);

        //When & Then
        mockMvc.perform(get("/v1/chronicDisease/pet/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].petId", is(1)))
                .andExpect(jsonPath("$[0].chronicDiseaseId", is(1)))
                .andExpect(jsonPath("$[0].dateOfDiagnosis", is(LocalDate.now().toString())));
    }

    @Test
    public void getChronicDisease() throws Exception {
        //Given
        ChronicDiseaseDto chronicDiseaseDto = new ChronicDiseaseDto(1L, "name", Arrays.asList(new ChronicDisease_PetDto[]{}));
        ChronicDisease chronicDisease = new ChronicDisease(1L, "name", Arrays.asList(new ChronicDisease_Pet[]{}));

        when(chronicDiseaseService.getChronicDisease(1L)).thenReturn(Optional.of(chronicDisease));
        when(chronicDiseaseMapper.mapToChronicDiseaseDto(chronicDisease)).thenReturn(chronicDiseaseDto);

        //When & Then
        mockMvc.perform(get("/v1/chronicDisease/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.chronicDisease_PetDtoList", is(new ArrayList<>())));
    }

    @Test
    public void deleteChronicDisease() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/chronicDisease/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePetChronicDisease() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/chronicDisease/pet/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateChronicDisease() throws Exception {
        //Given
        ChronicDiseaseDto chronicDiseaseDto = new ChronicDiseaseDto(1L, "name", Arrays.asList(new ChronicDisease_PetDto[]{}));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(chronicDiseaseDto);

        when(chronicDiseaseMapper.mapToChronicDiseaseDto(chronicDiseaseService.saveChronicDisease(chronicDiseaseMapper.mapToUpdatedChronicDisease(chronicDiseaseDto)))).thenReturn(chronicDiseaseDto);

        //When & Then
        mockMvc.perform(put("/v1/chronicDisease").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void createChronicDisease() throws Exception {
        //Given
        ChronicDiseaseDto chronicDiseaseDto = new ChronicDiseaseDto(1L, "name", Arrays.asList(new ChronicDisease_PetDto[]{}));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(chronicDiseaseDto);

        //When & Then
        mockMvc.perform(post("/v1/chronicDisease").contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void addChronicDisease() throws Exception {
        //Given
        ChronicDisease_PetDto chronicDisease_petDto = new ChronicDisease_PetDto(1L, 1L, 1L, LocalDate.now());

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(chronicDisease_petDto);

        //When & Then
        mockMvc.perform(post("/v1/chronicDisease/pet").contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}