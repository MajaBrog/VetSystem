package com.kodilla.veterinary.backend.controller;

import com.google.gson.Gson;
import com.kodilla.veterinary.backend.domain.*;
import com.kodilla.veterinary.backend.facade.FilterFacade;
import com.kodilla.veterinary.backend.mapper.VaccinationMapper;
import com.kodilla.veterinary.backend.service.VaccinationService;
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
@WebMvcTest(value = VaccinationController.class)
public class VaccinationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FilterFacade filterFacade;

    @MockBean
    private VaccinationService vaccinationService;

    @MockBean
    private VaccinationMapper vaccinationMapper;

    @Test
    public void shouldGetEmptyList() throws Exception {
        List<VaccinationDto> vaccinationDtos = new ArrayList<>();
        when(vaccinationMapper.mapToVaccinationDtoList(vaccinationService.getAllVaccinations())).thenReturn(vaccinationDtos);

        //When & Then
        mockMvc.perform(get("/v1/vaccination")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void getVaccinations() throws Exception {
        VaccinationDto vaccinationDto = new VaccinationDto(1l, "name", "disease", "dosePerKg", Unit.MG, true, 1L, Arrays.asList(new Visit_VaccinationDto[]{}));
        List<VaccinationDto> vaccinationDtos = new ArrayList<>();
        vaccinationDtos.add(vaccinationDto);
        when(vaccinationMapper.mapToVaccinationDtoList(vaccinationService.getAllVaccinations())).thenReturn(vaccinationDtos);

        //When & Then
        mockMvc.perform(get("/v1/vaccination")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].disease", is("disease")))
                .andExpect(jsonPath("$[0].dosePerKg", is("dosePerKg")))
                .andExpect(jsonPath("$[0].unit", is("MG")))
                .andExpect(jsonPath("$[0].mandatory", is(true)))
                .andExpect(jsonPath("$[0].intervalInWeeks", is(1)))
                .andExpect(jsonPath("$[0].visit_vaccinationsDtoList", is(new ArrayList<>())));
    }

    @Test
    public void filterVaccinationByLastName() throws Exception {
        VaccinationDto vaccinationDto = new VaccinationDto(1l, "name", "disease", "dosePerKg", Unit.MG, true, 1L, Arrays.asList(new Visit_VaccinationDto[]{}));
        List<VaccinationDto> vaccinationDtos = new ArrayList<>();
        vaccinationDtos.add(vaccinationDto);
        when(vaccinationMapper.mapToVaccinationDtoList(filterFacade.filterVaccinations("na"))).thenReturn(vaccinationDtos);

        //When & Then
        mockMvc.perform(get("/v1/vaccination/filter/na")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].disease", is("disease")))
                .andExpect(jsonPath("$[0].dosePerKg", is("dosePerKg")))
                .andExpect(jsonPath("$[0].unit", is("MG")))
                .andExpect(jsonPath("$[0].mandatory", is(true)))
                .andExpect(jsonPath("$[0].intervalInWeeks", is(1)))
                .andExpect(jsonPath("$[0].visit_vaccinationsDtoList", is(new ArrayList<>())));
    }

    @Test
    public void getVaccination() throws Exception {
        //Given
        VaccinationDto vaccinationDto = new VaccinationDto(1l, "name", "disease", "dosePerKg", Unit.MG, true, 1L, Arrays.asList(new Visit_VaccinationDto[]{}));
        Vaccination vaccination = new Vaccination(1l, "name", "disease", "dosePerKg", Unit.MG, true, 1L, Arrays.asList(new Visit_Vaccination[]{}));

        when(vaccinationService.getVaccination(1L)).thenReturn(Optional.of(vaccination));
        when(vaccinationMapper.mapToVaccinationDto(vaccination)).thenReturn(vaccinationDto);

        //When & Then
        mockMvc.perform(get("/v1/vaccination/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.disease", is("disease")))
                .andExpect(jsonPath("$.dosePerKg", is("dosePerKg")))
                .andExpect(jsonPath("$.unit", is("MG")))
                .andExpect(jsonPath("$.mandatory", is(true)))
                .andExpect(jsonPath("$.intervalInWeeks", is(1)))
                .andExpect(jsonPath("$.visit_vaccinationsDtoList", is(new ArrayList<>())));
    }

    @Test
    public void deleteVaccination() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/vaccination/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateVaccination() throws Exception {
        //Given
        VaccinationDto vaccinationDto = new VaccinationDto(1l, "name", "disease", "dosePerKg", Unit.MG, true, 1L, Arrays.asList(new Visit_VaccinationDto[]{}));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(vaccinationDto);

        when(vaccinationMapper.mapToVaccinationDto(vaccinationService.saveVaccination(vaccinationMapper.mapToUpdatedVaccination(vaccinationDto)))).thenReturn(vaccinationDto);

        //When & Then
        mockMvc.perform(put("/v1/vaccination").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.disease", is("disease")))
                .andExpect(jsonPath("$.dosePerKg", is("dosePerKg")))
                .andExpect(jsonPath("$.unit", is("MG")))
                .andExpect(jsonPath("$.mandatory", is(true)))
                .andExpect(jsonPath("$.intervalInWeeks", is(1)))
                .andExpect(jsonPath("$.visit_vaccinationsDtoList", is(new ArrayList<>())));
    }

    @Test
    public void createVaccination() throws Exception {
        //Given
        VaccinationDto vaccinationDto = new VaccinationDto(1l, "name", "disease", "dosePerKg", Unit.MG, true, 1L, Arrays.asList(new Visit_VaccinationDto[]{}));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(vaccinationDto);

        //When & Then
        mockMvc.perform(post("/v1/vaccination").contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}