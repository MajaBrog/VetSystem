package com.kodilla.veterinary.backend.service;

import com.kodilla.veterinary.backend.convert.client.ConvertClient;
import com.kodilla.veterinary.backend.domain.*;
import com.kodilla.veterinary.backend.repository.ChronicDiseaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChronicDiseaseServiceTest {
    @InjectMocks

    private ChronicDiseaseService chronicDiseaseService;

    @Mock
    private ChronicDiseaseRepository chronicDiseaseRepository;

    @Mock
    private ConvertClient convertClient;


    @Test
    public void getAllChronicDiseases() {
        //Given
        ChronicDisease chronicDisease1 = new ChronicDisease(1L,"name",Arrays.asList(new ChronicDisease_Pet[]{}));
        ChronicDisease chronicDisease2 = new ChronicDisease(2L,"name",Arrays.asList(new ChronicDisease_Pet[]{}));
        List<ChronicDisease> chronicDiseases=new ArrayList<>();
        chronicDiseases.add(chronicDisease1);
        chronicDiseases.add(chronicDisease2);

        when(chronicDiseaseRepository.findAll()).thenReturn(chronicDiseases);
        //When
        List allChronicDiseases = chronicDiseaseService.getAllChronicDiseases();
        //Then
        assertEquals(2, allChronicDiseases.size());
    }

    @Test
    public void getChronicDisease() {
        //Given
        ChronicDisease chronicDisease1 = new ChronicDisease(1L, "name",Arrays.asList(new ChronicDisease_Pet[]{}));

        when(chronicDiseaseRepository.findById(1L)).thenReturn(Optional.of(chronicDisease1));
        //When
        ChronicDisease chronicDisease = chronicDiseaseService.getChronicDisease(1L).get();
        //Then
        assertEquals(1L, chronicDisease.getId(), 0);
        assertEquals("name", chronicDisease.getName());
        assertEquals(new ArrayList<>(), chronicDisease.getChronicDisease_Pets());

    }

    @Test
    public void saveChronicDisease() {
        //Given
        ChronicDisease chronicDisease1 = new ChronicDisease(1L, "name",Arrays.asList(new ChronicDisease_Pet[]{}));
        when(chronicDiseaseRepository.save(chronicDisease1)).thenReturn(chronicDisease1);
        //When
        ChronicDisease chronicDisease = chronicDiseaseService.saveChronicDisease(chronicDisease1);
        //Then
        assertEquals(1L, chronicDisease.getId(), 0);
        assertEquals("name", chronicDisease.getName());
        assertEquals(new ArrayList<>(), chronicDisease.getChronicDisease_Pets());
    }


    @Test
    public void deleteChronicDisease() {

        //When
        chronicDiseaseService.deleteChronicDisease(1L);
        //Then
        Mockito.verify(chronicDiseaseRepository, times(1)).deleteById(1L);
    }
}