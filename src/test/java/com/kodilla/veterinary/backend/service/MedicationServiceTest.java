package com.kodilla.veterinary.backend.service;

import com.kodilla.veterinary.backend.convert.client.ConvertClient;
import com.kodilla.veterinary.backend.domain.Medication;
import com.kodilla.veterinary.backend.domain.Unit;
import com.kodilla.veterinary.backend.domain.Visit_Medication;
import com.kodilla.veterinary.backend.repository.MedicationRepository;
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
public class MedicationServiceTest {
    @InjectMocks

    private MedicationService medicationService;

    @Mock
    private MedicationRepository medicationRepository;

    @Mock
    private ConvertClient convertClient;

    @Test
    public void getAllMedications() {
        //Given
        Medication medication1 = new Medication(1L, "name", "dosePerKg", Unit.MG, Arrays.asList(new Visit_Medication[]{}));
        Medication medication2 = new Medication(2L, "name", "dosePerKg", Unit.MG, Arrays.asList(new Visit_Medication[]{}));
        List<Medication> medications=new ArrayList<>();
        medications.add(medication1);
        medications.add(medication2);

        when(medicationRepository.findAll()).thenReturn(medications);
        //When
        List allMedications = medicationService.getAllMedications();
        //Then
        assertEquals(2, allMedications.size());
    }

    @Test
    public void getMedication() {
        //Given
        Medication medication1 = new Medication(1L, "name", "dosePerKg", Unit.MG, Arrays.asList(new Visit_Medication[]{}));

        when(medicationRepository.findById(1L)).thenReturn(Optional.of(medication1));
        //When
        Medication medication = medicationService.getMedication(1L).get();
        //Then
        assertEquals(1L, medication.getId(),0);
        assertEquals("name",medication.getMedicationName());
        assertEquals("dosePerKg",medication.getDosePerKg());
        assertEquals(Unit.MG,medication.getUnit());
        assertEquals(new ArrayList<>(),medication.getVisit_medications());
    }

    @Test
    public void saveMedication() {
        //Given
        Medication medication1 = new Medication(1L, "name", "dosePerKg", Unit.G, Arrays.asList(new Visit_Medication[]{}));

        when(medicationRepository.save(medication1)).thenReturn(medication1);
        //When
        Medication medication = medicationService.saveMedication(medication1);
        //Then
        assertEquals(2L, medication.getId(),0);
        assertEquals("name",medication.getMedicationName());
        assertEquals("dosePerKg",medication.getDosePerKg());
        assertEquals(Unit.G,medication.getUnit());
        assertEquals(new ArrayList<>(),medication.getVisit_medications());
    }


    @Test
    public void deleteMedication() {

        //When
        medicationService.deleteMedication(1L);
        //Then
        Mockito.verify(medicationRepository, times(1)).deleteById(1L);
    }
}