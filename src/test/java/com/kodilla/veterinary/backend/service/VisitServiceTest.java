package com.kodilla.veterinary.backend.service;

import com.kodilla.veterinary.backend.convert.client.ConvertClient;
import com.kodilla.veterinary.backend.domain.*;
import com.kodilla.veterinary.backend.repository.VisitRepository;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VisitServiceTest {
    @InjectMocks

    private VisitService visitService;

    @Mock
    private VisitRepository visitRepository;

    @Mock
    private ConvertClient convertClient;

    private Pet pet;

    @Before
    public void init() {
        Address address=new Address("street", 1, 2, "city", "postcode");
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
                .build();    }

    @Test
    public void getAllVisits() {
        //Given
        Visit visit1 = new Visit(1L, LocalDate.now(), pet,  "diagnose",  "additionalRecommendation", 15,Arrays.asList(new Visit_Medication[]{}) ,Arrays.asList(new Visit_Vaccination[]{}));
        Visit visit2 = new Visit(2L, LocalDate.now(), pet,  "diagnose",  "additionalRecommendation", 15,Arrays.asList(new Visit_Medication[]{}) ,Arrays.asList(new Visit_Vaccination[]{}));
        List<Visit> visits = new ArrayList<>();
        visits.add(visit1);
        visits.add(visit2);

        when(visitRepository.findAll()).thenReturn(visits);
        //When
        List allVisits = visitService.getAllVisits();
        //Then
        assertEquals(2, allVisits.size());
    }
    @Test
    public void getPetVisits() {
        //Given
        Visit visit1 = new Visit(1L, LocalDate.now(), pet,  "diagnose",  "additionalRecommendation", 15,Arrays.asList(new Visit_Medication[]{}) ,Arrays.asList(new Visit_Vaccination[]{}));
        Visit visit2 = new Visit(2L, LocalDate.now(), pet,  "diagnose",  "additionalRecommendation", 15,Arrays.asList(new Visit_Medication[]{}) ,Arrays.asList(new Visit_Vaccination[]{}));
        List<Visit> visits = new ArrayList<>();
        visits.add(visit1);
        visits.add(visit2);

        when(visitRepository.findAll()).thenReturn(visits);
        //When
        List allVisits = visitService.getPetVisits(1L);
        //Then
        assertEquals(2, allVisits.size());
    }
    @Test
    public void getVisit() {
        //Given
        Visit visit1 = new Visit(1L, LocalDate.now(), pet,  "diagnose",  "additionalRecommendation", 15,Arrays.asList(new Visit_Medication[]{}) ,Arrays.asList(new Visit_Vaccination[]{}));

        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit1));
        //When
        Visit visit = visitService.getVisit(1L).get();
        //Then
        assertEquals(1L, visit.getId(), 0);
        assertEquals(LocalDate.now(), visit.getDateOfVisit());
        assertEquals(pet, visit.getPet());
        assertEquals("diagnose", visit.getDiagnose());
        assertEquals("additionalRecommendation", visit.getAdditionalRecommendation());
        assertEquals(15, visit.getWeight());
        assertEquals(new ArrayList<>(), visit.getVisit_medications());
        assertEquals(new ArrayList<>(), visit.getVisit_vaccinations());
    }

    @Test
    public void saveVisit() {
        //Given
        Visit visit1 = new Visit(1L, LocalDate.now(), pet,  "diagnose",  "additionalRecommendation", 15,Arrays.asList(new Visit_Medication[]{}) ,Arrays.asList(new Visit_Vaccination[]{}));

        when(visitRepository.save(visit1)).thenReturn(visit1);
        //When
        Visit visit = visitService.saveVisit(visit1);
        //Then
        assertEquals(1L, visit.getId(), 0);
        assertEquals(LocalDate.now(), visit.getDateOfVisit());
        assertEquals(pet, visit.getPet());
        assertEquals("diagnose", visit.getDiagnose());
        assertEquals("additionalRecommendation", visit.getAdditionalRecommendation());
        assertEquals(15, visit.getWeight());
        assertEquals(new ArrayList<>(), visit.getVisit_medications());
        assertEquals(new ArrayList<>(), visit.getVisit_vaccinations());
    }


    @Test
    public void deleteVisit() {
        //When
        visitService.deleteVisit(1L);
        //Then
        Mockito.verify(visitRepository, times(1)).deleteById(1L);
    }
}