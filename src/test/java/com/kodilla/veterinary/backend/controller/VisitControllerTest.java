package com.kodilla.veterinary.backend.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.veterinary.LocalDateAdapter;
import com.kodilla.veterinary.backend.domain.*;
import com.kodilla.veterinary.backend.facade.FilterFacade;
import com.kodilla.veterinary.backend.mapper.VisitMapper;
import com.kodilla.veterinary.backend.service.VisitService;
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
@WebMvcTest(value = VisitController.class)
public class VisitControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FilterFacade filterFacade;

    @MockBean
    private VisitService visitService;

    @MockBean
    private VisitMapper visitMapper;

    private Client client;
    private Pet pet;
    private Visit visit;
    private VisitDto visitDto;

    @Before
    public void init() {
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
        visitDto = new VisitDto(1L, LocalDate.now(), 1L, "diagnose", "additionalRecommendation", 15, Arrays.asList(new Visit_MedicationDto[]{}), Arrays.asList(new Visit_VaccinationDto[]{}));
        visit = new Visit(1L, LocalDate.now(), pet, "diagnose", "additionalRecommendation", 15, Arrays.asList(new Visit_Medication[]{}), Arrays.asList(new Visit_Vaccination[]{}));

    }

    @Test
    public void shouldGetEmptyList() throws Exception {
        List<VisitDto> visitDtos = new ArrayList<>();
        when(visitMapper.mapToVisitDtoList(visitService.getAllVisits())).thenReturn(visitDtos);

        //When & Then
        mockMvc.perform(get("/v1/visit")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void getVisits() throws Exception {
        List<VisitDto> visitDtos = new ArrayList<>();
        visitDtos.add(visitDto);
        when(visitMapper.mapToVisitDtoList(visitService.getAllVisits())).thenReturn(visitDtos);

        //When & Then
        mockMvc.perform(get("/v1/visit")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].dateOfVisit", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$[0].petId", is(1)))
                .andExpect(jsonPath("$[0].diagnose", is("diagnose")))
                .andExpect(jsonPath("$[0].additionalRecommendation", is("additionalRecommendation")))
                .andExpect(jsonPath("$[0].weight", is(15)))
                .andExpect(jsonPath("$[0].visit_medicationsDtoList", is(new ArrayList<>())))
                .andExpect(jsonPath("$[0].visit_vaccinationsDtoList", is(new ArrayList<>())));
    }

    @Test
    public void getPetVisits() throws Exception {
        List<VisitDto> visitDtos = new ArrayList<>();
        visitDtos.add(visitDto);
        when(visitMapper.mapToVisitDtoList(visitService.getPetVisits(1L))).thenReturn(visitDtos);

        //When & Then
        mockMvc.perform(get("/v1/visit/pet/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].dateOfVisit", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$[0].petId", is(1)))
                .andExpect(jsonPath("$[0].diagnose", is("diagnose")))
                .andExpect(jsonPath("$[0].additionalRecommendation", is("additionalRecommendation")))
                .andExpect(jsonPath("$[0].weight", is(15)))
                .andExpect(jsonPath("$[0].visit_medicationsDtoList", is(new ArrayList<>())))
                .andExpect(jsonPath("$[0].visit_vaccinationsDtoList", is(new ArrayList<>())));
    }

    @Test
    public void filterVisitByLastName() throws Exception {
        List<VisitDto> visitDtos = new ArrayList<>();
        visitDtos.add(visitDto);
        when(visitMapper.mapToVisitDtoList(filterFacade.filterVisits("na"))).thenReturn(visitDtos);

        //When & Then
        mockMvc.perform(get("/v1/visit/filter/na")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].dateOfVisit", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$[0].petId", is(1)))
                .andExpect(jsonPath("$[0].diagnose", is("diagnose")))
                .andExpect(jsonPath("$[0].additionalRecommendation", is("additionalRecommendation")))
                .andExpect(jsonPath("$[0].weight", is(15)))
                .andExpect(jsonPath("$[0].visit_medicationsDtoList", is(new ArrayList<>())))
                .andExpect(jsonPath("$[0].visit_vaccinationsDtoList", is(new ArrayList<>())));
    }

    @Test
    public void getVisit() throws Exception {
        //Given

        when(visitService.getVisit(1L)).thenReturn(Optional.of(visit));
        when(visitMapper.mapToVisitDto(visit)).thenReturn(visitDto);

        //When & Then
        mockMvc.perform(get("/v1/visit/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.dateOfVisit", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$.petId", is(1)))
                .andExpect(jsonPath("$.diagnose", is("diagnose")))
                .andExpect(jsonPath("$.additionalRecommendation", is("additionalRecommendation")))
                .andExpect(jsonPath("$.weight", is(15)))
                .andExpect(jsonPath("$.visit_medicationsDtoList", is(new ArrayList<>())))
                .andExpect(jsonPath("$.visit_vaccinationsDtoList", is(new ArrayList<>())));
    }

    @Test
    public void deleteVisit() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/visit/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateVisit() throws Exception {
        //Given
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(visitDto);

        when(visitMapper.mapToVisitDto(visitService.saveVisit(visitMapper.mapToUpdatedVisit(visitDto)))).thenReturn(visitDto);

        //When & Then
        mockMvc.perform(put("/v1/visit").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.dateOfVisit", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$.petId", is(1)))
                .andExpect(jsonPath("$.diagnose", is("diagnose")))
                .andExpect(jsonPath("$.additionalRecommendation", is("additionalRecommendation")))
                .andExpect(jsonPath("$.weight", is(15)))
                .andExpect(jsonPath("$.visit_medicationsDtoList", is(new ArrayList<>())))
                .andExpect(jsonPath("$.visit_vaccinationsDtoList", is(new ArrayList<>())));
    }

    @Test
    public void createVisit() throws Exception {
        //Given

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(visitDto);

        //When & Then
        mockMvc.perform(post("/v1/visit").contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.dateOfVisit", is(LocalDate.now().toString())))
//                .andExpect(jsonPath("$.petId", is(1)))
//                .andExpect(jsonPath("$.diagnose", is("diagnose")))
//                .andExpect(jsonPath("$.additionalRecommendation", is("additionalRecommendation")))
//                .andExpect(jsonPath("$.weight", is(15)))
//                .andExpect(jsonPath("$.visit_medicationsDtoList", is(new ArrayList<>())))
//                .andExpect(jsonPath("$.visit_vaccinationsDtoList", is(new ArrayList<>())));
        ;
    }
}
