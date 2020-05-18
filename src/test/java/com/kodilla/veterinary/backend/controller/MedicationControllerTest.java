package com.kodilla.veterinary.backend.controller;

import com.google.gson.Gson;
import com.kodilla.veterinary.backend.domain.*;
import com.kodilla.veterinary.backend.facade.FilterFacade;
import com.kodilla.veterinary.backend.mapper.MedicationMapper;
import com.kodilla.veterinary.backend.service.MedicationService;
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
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MedicationController.class)
public class MedicationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FilterFacade filterFacade;

    @MockBean
    private MedicationService medicationService;

    @MockBean
    private MedicationMapper medicationMapper;

    @Test
    public void shouldGetEmptyList() throws Exception {
        List<MedicationDto> medicationDtos = new ArrayList<>();
        when(medicationMapper.mapToMedicationDtoList(medicationService.getAllMedications())).thenReturn(medicationDtos);

        //When & Then
        mockMvc.perform(get("/v1/medication")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void getMedications() throws Exception {
        MedicationDto medicationDto = new MedicationDto(1l, "name", "dosePerKg", Unit.MG, Arrays.asList(new Visit_MedicationDto[]{}));
        List<MedicationDto> medicationDtos = new ArrayList<>();
        medicationDtos.add(medicationDto);
        when(medicationMapper.mapToMedicationDtoList(medicationService.getAllMedications())).thenReturn(medicationDtos);

        //When & Then
        mockMvc.perform(get("/v1/medication")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].medicationName", is("name")))
                .andExpect(jsonPath("$[0].dosePerKg", is("dosePerKg")))
                .andExpect(jsonPath("$[0].unit", is("MG")))
                .andExpect(jsonPath("$[0].visit_medicationDtoList", is(new ArrayList<>())));
    }
    @Test
    public void filterMedicationByLastName() throws Exception {
        MedicationDto medicationDto = new MedicationDto(1l, "name", "dosePerKg", Unit.MG, Arrays.asList(new Visit_MedicationDto[]{}));
        List<MedicationDto> medicationDtos = new ArrayList<>();
        medicationDtos.add(medicationDto);
        when(medicationMapper.mapToMedicationDtoList(filterFacade.filterMedications("na"))).thenReturn(medicationDtos);

        //When & Then
        mockMvc.perform(get("/v1/medication/filter/na")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].medicationName", is("name")))
                .andExpect(jsonPath("$[0].dosePerKg", is("dosePerKg")))
                .andExpect(jsonPath("$[0].unit", is("MG")))
                .andExpect(jsonPath("$[0].visit_medicationDtoList", is(new ArrayList<>())));
    }

    @Test
    public void getMedication() throws Exception {
        //Given
        MedicationDto medicationDto =new MedicationDto(1L,  "name",   "dosePerKg",  Unit.MG,  Arrays.asList(new Visit_MedicationDto[]{}));
        Medication medication =new Medication(1L,  "name",   "dosePerKg",  Unit.MG,  Arrays.asList(new Visit_Medication[]{}));

        when(medicationService.getMedication(1L)).thenReturn(Optional.of(medication));
        when(medicationMapper.mapToMedicationDto(medication)).thenReturn(medicationDto);

        //When & Then
        mockMvc.perform(get("/v1/medication/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.medicationName", is("name")))
                .andExpect(jsonPath("$.dosePerKg", is("dosePerKg")))
                .andExpect(jsonPath("$.unit", is("MG")))
                .andExpect(jsonPath("$.visit_medicationDtoList", is(new ArrayList<>())));
    }
    @Test
    public void deleteMedication() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/medication/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateMedication() throws Exception {
        //Given
        MedicationDto medicationDto =new MedicationDto(1L,  "name",   "dosePerKg",  Unit.MG,  Arrays.asList(new Visit_MedicationDto[]{}));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(medicationDto);

        when(medicationMapper.mapToMedicationDto(medicationService.saveMedication(medicationMapper.mapToUpdatedMedication(medicationDto)))).thenReturn(medicationDto);

        //When & Then
        mockMvc.perform(put("/v1/medication").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.medicationName", is("name")))
                .andExpect(jsonPath("$.dosePerKg", is("dosePerKg")))
                .andExpect(jsonPath("$.unit", is("MG")))
                .andExpect(jsonPath("$.visit_medicationDtoList", is(new ArrayList<>())));
    }

    @Test
    public void createMedication() throws Exception {
        //Given
        MedicationDto medicationDto =new MedicationDto(1L,  "name",   "dosePerKg",  Unit.MG,  Arrays.asList(new Visit_MedicationDto[]{}));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(medicationDto);

        //When & Then
        mockMvc.perform(post("/v1/medication").contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}