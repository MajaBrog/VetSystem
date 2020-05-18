package com.kodilla.veterinary.backend.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.veterinary.LocalDateAdapter;
import com.kodilla.veterinary.backend.domain.*;
import com.kodilla.veterinary.backend.facade.FilterFacade;
import com.kodilla.veterinary.backend.mapper.Visit_MedicationMapper;
import com.kodilla.veterinary.backend.service.Visit_MedicationService;
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
@WebMvcTest(value = Visit_MedicationController.class)
public class Visit_MedicationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FilterFacade filterFacade;

    @MockBean
    private Visit_MedicationService visit_medicationService;

    @MockBean
    private Visit_MedicationMapper visit_medicationMapper;

    private Visit_Medication visit_medication;
    private Visit_MedicationDto visit_medicationDto;

    @Before
    public void init() {
        Address address = new Address("street", 1, 2, "city", "postcode");
        Client client = Client.builder()
                .id(1L)
                .legalID("93040508806")
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("369050542")
                .address(address)
                .email("email")
                .pets(Arrays.asList(new Pet[]{}))
                .build();
        Pet pet = Pet.builder()
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
        Medication medication = new Medication(1l, "name", "dosePerKg", Unit.MG, Arrays.asList(new Visit_Medication[]{}));
        Visit visit = new Visit(1L, LocalDate.now(), pet, "diagnose", "additionalRecommendation", 15, Arrays.asList(new Visit_Medication[]{}), Arrays.asList(new Visit_Vaccination[]{}));
        visit_medicationDto = new Visit_MedicationDto(1L, 1L, 1L, "dose", Unit.MG);
        visit_medication = new Visit_Medication(1L, visit, medication, "dose", Unit.MG);
    }

    @Test
    public void shouldGetEmptyList() throws Exception {
        List<Visit_MedicationDto> Visit_MedicationDtos = new ArrayList<>();
        when(visit_medicationMapper.mapToVisit_MedicationDtoList(visit_medicationService.getAllVisit_Medications())).thenReturn(Visit_MedicationDtos);

        //When & Then
        mockMvc.perform(get("/v1/visit_Medication")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void getVisit_Medications() throws Exception {
        List<Visit_MedicationDto> visit_medicationDtos = new ArrayList<>();
        visit_medicationDtos.add(visit_medicationDto);
        when(visit_medicationMapper.mapToVisit_MedicationDtoList(visit_medicationService.getAllVisit_Medications())).thenReturn(visit_medicationDtos);

        //When & Then
        mockMvc.perform(get("/v1/visit_Medication")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].visitId", is(1)))
                .andExpect(jsonPath("$[0].medicationId", is(1)))
                .andExpect(jsonPath("$[0].dose", is("dose")))
                .andExpect(jsonPath("$[0].unit", is("MG")));

    }

    @Test
    public void getVisitMedications() throws Exception {
        List<Visit_MedicationDto> visit_medicationDtos = new ArrayList<>();
        visit_medicationDtos.add(visit_medicationDto);
        when(visit_medicationMapper.mapToVisit_MedicationDtoList(visit_medicationService.getVisitMedications(1L))).thenReturn(visit_medicationDtos);

        //When & Then
        mockMvc.perform(get("/v1/visit_Medication/visit/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].visitId", is(1)))
                .andExpect(jsonPath("$[0].medicationId", is(1)))
                .andExpect(jsonPath("$[0].dose", is("dose")))
                .andExpect(jsonPath("$[0].unit", is("MG")));

    }


    @Test
    public void getVisit_Medication() throws Exception {
        //Given

        when(visit_medicationService.getVisit_Medication(1L)).thenReturn(Optional.of(visit_medication));
        when(visit_medicationMapper.mapToVisit_MedicationDto(visit_medication)).thenReturn(visit_medicationDto);

        //When & Then
        mockMvc.perform(get("/v1/visit_Medication/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.visitId", is(1)))
                .andExpect(jsonPath("$.medicationId", is(1)))
                .andExpect(jsonPath("$.dose", is("dose")))
                .andExpect(jsonPath("$.unit", is("MG")));
    }

    @Test
    public void deleteVisit_Medication() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/visit_Medication/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void createVisit_Medication() throws Exception {
        //Given

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(visit_medicationDto);

        //When & Then
        mockMvc.perform(post("/v1/visit_Medication").contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

}