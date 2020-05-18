package com.kodilla.veterinary.backend.service;

import com.kodilla.veterinary.backend.convert.client.ConvertClient;
import com.kodilla.veterinary.backend.domain.Vaccination;
import com.kodilla.veterinary.backend.domain.Unit;
import com.kodilla.veterinary.backend.domain.Visit_Vaccination;
import com.kodilla.veterinary.backend.repository.VaccinationRepository;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VaccinationServiceTest {
    @InjectMocks

    private VaccinationService vaccinationService;

    @Mock
    private VaccinationRepository vaccinationRepository;

    @Mock
    private ConvertClient convertClient;

    @Test
    public void getAllVaccinations() {
        //Given
        Vaccination vaccination1 = new Vaccination(1L,  "name1",   "disease",   "dosePerKg",  Unit.MG, true ,   2L,Arrays.asList(new Visit_Vaccination[]{}));
        Vaccination vaccination2 = new Vaccination(2L,  "name2",   "disease",   "dosePerKg",  Unit.MG, true ,   2L,Arrays.asList(new Visit_Vaccination[]{}));
        List<Vaccination> vaccinations=new ArrayList<>();
        vaccinations.add(vaccination1);
        vaccinations.add(vaccination2);

        when(vaccinationRepository.findAll()).thenReturn(vaccinations);
        //When
        List allVaccinations = vaccinationService.getAllVaccinations();
        //Then
        assertEquals(2, allVaccinations.size());
    }

    @Test
    public void getVaccination() {
        //Given
        Vaccination vaccination1 = new Vaccination(1L,  "name",   "disease",   "dosePerKg",  Unit.MG, true ,   2L,Arrays.asList(new Visit_Vaccination[]{}));;

        when(vaccinationRepository.findById(1L)).thenReturn(Optional.of(vaccination1));
        //When
        Vaccination vaccination = vaccinationService.getVaccination(1L).get();
        //Then
        assertEquals(1L, vaccination.getId(),0);
        assertEquals("name",vaccination.getName());
        assertEquals("disease",vaccination.getDisease());
        assertEquals("dosePerKg",vaccination.getDosePerKg());
        assertEquals(Unit.MG,vaccination.getUnit());
        assertEquals(true,vaccination.isMandatory());
        assertEquals(2,vaccination.getIntervalInWeeks());
        assertEquals(new ArrayList<>(),vaccination.getVisit_vaccinations());
    }

    @Test
    public void saveVaccination() {
        //Given
        Vaccination vaccination1 = new Vaccination(1L,  "name",   "disease",   "dosePerKg",  Unit.MG, true ,   2L,Arrays.asList(new Visit_Vaccination[]{}));

        when(vaccinationRepository.save(vaccination1)).thenReturn(vaccination1);
        //When
        Vaccination vaccination = vaccinationService.saveVaccination(vaccination1);
        //Then
        assertEquals(1L, vaccination.getId(),0);
        assertEquals("name",vaccination.getName());
        assertEquals("disease",vaccination.getDisease());
        assertEquals("dosePerKg",vaccination.getDosePerKg());
        assertEquals(Unit.MG,vaccination.getUnit());
        assertEquals(true,vaccination.isMandatory());
        assertEquals(2,vaccination.getIntervalInWeeks());
        assertEquals(new ArrayList<>(),vaccination.getVisit_vaccinations());
    }


    @Test
    public void deleteVaccination() {

        //When
        vaccinationService.deleteVaccination(1L);
        //Then
        Mockito.verify(vaccinationRepository, times(1)).deleteById(1L);
    }
}