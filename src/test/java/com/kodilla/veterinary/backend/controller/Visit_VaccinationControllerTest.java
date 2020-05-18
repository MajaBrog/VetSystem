package com.kodilla.veterinary.backend.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.veterinary.LocalDateAdapter;
import com.kodilla.veterinary.backend.domain.*;
import com.kodilla.veterinary.backend.facade.FilterFacade;
import com.kodilla.veterinary.backend.mapper.Visit_VaccinationMapper;
import com.kodilla.veterinary.backend.service.Visit_VaccinationService;
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
@WebMvcTest(value = Visit_VaccinationController.class)
public class Visit_VaccinationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FilterFacade filterFacade;

    @MockBean
    private Visit_VaccinationService visit_vaccinationService;

    @MockBean
    private Visit_VaccinationMapper visit_vaccinationMapper;

    private Visit_Vaccination visit_vaccination;
    private Visit_VaccinationDto visit_vaccinationDto;

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
        Vaccination vaccination = new Vaccination(1L, "name", "disease", "dosePerKg", Unit.MG, true, 2L, Arrays.asList(new Visit_Vaccination[]{}));
        Visit visit = new Visit(1L, LocalDate.now(), pet, "diagnose", "additionalRecommendation", 15, Arrays.asList(new Visit_Medication[]{}), Arrays.asList(new Visit_Vaccination[]{}));

        visit_vaccinationDto = new Visit_VaccinationDto(1L, 1L, 1L, "dose", Unit.MG, LocalDate.now());
        visit_vaccination = new Visit_Vaccination(1L, visit, vaccination, "dose", Unit.MG, LocalDate.now());
    }

    @Test
    public void shouldGetEmptyList() throws Exception {
        List<Visit_VaccinationDto> petDtos = new ArrayList<>();
        when(visit_vaccinationMapper.mapToVisit_VaccinationDtoList(visit_vaccinationService.getAllVisit_Vaccinations())).thenReturn(petDtos);

        //When & Then
        mockMvc.perform(get("/v1/visit_Vaccination")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void getVisit_Vaccinations() throws Exception {
        List<Visit_VaccinationDto> Visit_VaccinationDtos = new ArrayList<>();
        Visit_VaccinationDtos.add(visit_vaccinationDto);
        when(visit_vaccinationMapper.mapToVisit_VaccinationDtoList(visit_vaccinationService.getAllVisit_Vaccinations())).thenReturn(Visit_VaccinationDtos);

        //When & Then
        mockMvc.perform(get("/v1/visit_Vaccination")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].visitId", is(1)))
                .andExpect(jsonPath("$[0].vaccinationId", is(1)))
                .andExpect(jsonPath("$[0].dose", is("dose")))
                .andExpect(jsonPath("$[0].unit", is("MG")))
                .andExpect(jsonPath("$[0].remindDate", is(LocalDate.now().toString())))
        ;
    }

    @Test
    public void getVisitVaccinations() throws Exception {
        List<Visit_VaccinationDto> Visit_VaccinationDtos = new ArrayList<>();
        Visit_VaccinationDtos.add(visit_vaccinationDto);
        when(visit_vaccinationMapper.mapToVisit_VaccinationDtoList(visit_vaccinationService.getVisitVaccinations(1L))).thenReturn(Visit_VaccinationDtos);

        //When & Then
        mockMvc.perform(get("/v1/visit_Vaccination/visit/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].visitId", is(1)))
                .andExpect(jsonPath("$[0].vaccinationId", is(1)))
                .andExpect(jsonPath("$[0].dose", is("dose")))
                .andExpect(jsonPath("$[0].unit", is("MG")))
                .andExpect(jsonPath("$[0].remindDate", is(LocalDate.now().toString())))
        ;
    }

    @Test
    public void getVisit_Vaccination() throws Exception {
        //Given

        when(visit_vaccinationService.getVisit_Vaccination(1L)).thenReturn(Optional.of(visit_vaccination));
        when(visit_vaccinationMapper.mapToVisit_VaccinationDto(visit_vaccination)).thenReturn(visit_vaccinationDto);

        //When & Then
        mockMvc.perform(get("/v1/visit_Vaccination/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.visitId", is(1)))
                .andExpect(jsonPath("$.vaccinationId", is(1)))
                .andExpect(jsonPath("$.dose", is("dose")))
                .andExpect(jsonPath("$.unit", is("MG")))
                .andExpect(jsonPath("$.remindDate", is(LocalDate.now().toString())));
    }

    @Test
    public void deleteVisit_Vaccination() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/visit_Vaccination/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void createVisit_Vaccination() throws Exception {
        //Given

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(visit_vaccinationDto);

        //When & Then
        mockMvc.perform(post("/v1/visit_Vaccination").contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}