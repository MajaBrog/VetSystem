package com.kodilla.veterinary.backend.service;

import com.kodilla.veterinary.backend.convert.client.ConvertClient;
import com.kodilla.veterinary.backend.domain.*;
import com.kodilla.veterinary.backend.repository.Visit_VaccinationRepository;
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
public class Visit_VaccinationServiceTest {
    @InjectMocks

    private Visit_VaccinationService visit_VaccinationService;

    @Mock
    private Visit_VaccinationRepository visit_VaccinationRepository;

    @Mock
    private ConvertClient convertClient;

    private Visit visit;
    private Vaccination vaccination;

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
        vaccination = new Vaccination(1l, "name", "disease", "dosePerKg", Unit.MG, true, 2L, Arrays.asList(new Visit_Vaccination[]{}));
        visit = new Visit(1L, LocalDate.now(), pet, "diagnose", "additionalRecommendation", 15, Arrays.asList(new Visit_Medication[]{}), Arrays.asList(new Visit_Vaccination[]{}));
        Visit_Vaccination visit_vaccination = new Visit_Vaccination(1L, visit, vaccination, "dose", Unit.MG, LocalDate.now());
    }

    @Test
    public void getAllVisit_Vaccinations() {
        //Given
        Visit_Vaccination visit_Vaccination1 = new Visit_Vaccination(1L, visit, vaccination, "dose", Unit.MG, LocalDate.now());
        Visit_Vaccination visit_Vaccination2 = new Visit_Vaccination(2L, visit, vaccination, "dose", Unit.MG, LocalDate.now());
        List<Visit_Vaccination> visit_Vaccinations = new ArrayList<>();
        visit_Vaccinations.add(visit_Vaccination1);
        visit_Vaccinations.add(visit_Vaccination2);

        when(visit_VaccinationRepository.findAll()).thenReturn(visit_Vaccinations);
        //When
        List allVisit_Vaccinations = visit_VaccinationService.getAllVisit_Vaccinations();
        //Then
        assertEquals(2, allVisit_Vaccinations.size());
    }

    @Test
    public void getVisit_Vaccination() {
        //Given
        Visit_Vaccination visit_Vaccination1 = new Visit_Vaccination(1L, visit, vaccination, "dose", Unit.MG, LocalDate.now());

        when(visit_VaccinationRepository.findById(1L)).thenReturn(Optional.of(visit_Vaccination1));
        //When
        Visit_Vaccination visit_Vaccination = visit_VaccinationService.getVisit_Vaccination(1L).get();
        //Then
        assertEquals(1L, visit_Vaccination.getId(), 0);
        assertEquals(visit, visit_Vaccination.getVisit());
        assertEquals(vaccination, visit_Vaccination.getVaccination());
        assertEquals("dose", visit_Vaccination.getDose());
        assertEquals(Unit.MG, visit_Vaccination.getUnit());
        assertEquals(LocalDate.now(), visit_Vaccination.getRemindDate());
    }

    @Test
    public void deleteVisit_Vaccination() {
        //When
        visit_VaccinationService.deleteVisit_Vaccination(1L);
        //Then
        Mockito.verify(visit_VaccinationRepository, times(1)).deleteById(1L);
    }
}