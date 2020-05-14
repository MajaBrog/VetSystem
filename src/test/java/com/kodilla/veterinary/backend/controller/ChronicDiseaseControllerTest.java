package com.kodilla.veterinary.backend.controller;

import com.kodilla.veterinary.backend.mapper.ChronicDiseaseMapper;
import com.kodilla.veterinary.backend.mapper.ChronicDisease_PetMapper;
import com.kodilla.veterinary.backend.service.ChronicDiseaseService;
import com.kodilla.veterinary.backend.service.ChronicDisease_PetService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@WebMvcTest(ChronicDiseaseController.class)
class ChronicDiseaseControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ChronicDiseaseService chronicDiseaseService;

    @Autowired
    private ChronicDiseaseMapper chronicDiseaseMapper;

    @Autowired
    private ChronicDisease_PetService chronicDisease_petService;

    @Autowired
    private ChronicDisease_PetMapper chronicDisease_petMapper;
    @Test
    void shouldGetEmptyList() {
    }
    @Test
    void shouldGetAllChronicDiseases() {
    }
    @Test
    void shouldGetChronicDisease() {
    }

    @Test
    void shouldFilterChronicDisease() {
    }

    @Test
    void shouldDeleteChronicDisease() {
    }

    @Test
    void shouldDeletePetChronicDisease() {
    }

    @Test
    void updateChronicDisease() {
    }

    @Test
    void shouldCreateChronicDisease() {
    }

    @Test
    void shouldAddChronicDisease() {
    }
}