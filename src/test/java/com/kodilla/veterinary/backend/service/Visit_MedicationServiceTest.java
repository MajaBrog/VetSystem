package com.kodilla.veterinary.backend.service;

import com.kodilla.veterinary.backend.convert.client.ConvertClient;
import com.kodilla.veterinary.backend.domain.*;
import com.kodilla.veterinary.backend.repository.Visit_MedicationRepository;
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
public class Visit_MedicationServiceTest {
    @InjectMocks

    private Visit_MedicationService visit_MedicationService;

    @Mock
    private Visit_MedicationRepository visit_MedicationRepository;

    @Mock
    private ConvertClient convertClient;

    private Visit visit;
    private Medication medication;

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
                .build();        medication = new Medication(1l, "name", "dosePerKg", Unit.MG, Arrays.asList(new Visit_Medication[]{}));
        visit = new Visit(1L, LocalDate.now(), pet, "diagnose", "additionalRecommendation", 15, Arrays.asList(new Visit_Medication[]{}), Arrays.asList(new Visit_Vaccination[]{}));
    }

    @Test
    public void getAllVisit_Medications() {
        //Given
        Visit_Medication visit_Medication1 = new Visit_Medication(1L, visit, medication, "dose", Unit.MG);
        Visit_Medication visit_Medication2 = new Visit_Medication(2L, visit, medication, "dose", Unit.MG);
        List<Visit_Medication> visit_Medications = new ArrayList<>();
        visit_Medications.add(visit_Medication1);
        visit_Medications.add(visit_Medication2);

        when(visit_MedicationRepository.findAll()).thenReturn(visit_Medications);
        //When
        List allVisit_Medications = visit_MedicationService.getAllVisit_Medications();
        //Then
        assertEquals(2, allVisit_Medications.size());
    }

    @Test
    public void getVisitMedications() {
        //Given
        Visit_Medication visit_Medication1 = new Visit_Medication(1L, visit, medication, "dose", Unit.MG);
        Visit_Medication visit_Medication2 = new Visit_Medication(2L, visit, medication, "dose", Unit.MG);
        List<Visit_Medication> visit_Medications = new ArrayList<>();
        visit_Medications.add(visit_Medication1);
        visit_Medications.add(visit_Medication2);

        when(visit_MedicationRepository.findAll()).thenReturn(visit_Medications);
        //When
        List allVisit_Medications = visit_MedicationService.getVisitMedications(1L);
        //Then
        assertEquals(2, allVisit_Medications.size());
    }

    @Test
    public void getVisit_Medication() {
        //Given
        Visit_Medication visit_Medication1 = new Visit_Medication(1L, visit, medication, "dose", Unit.MG);

        when(visit_MedicationRepository.findById(1L)).thenReturn(Optional.of(visit_Medication1));
        //When
        Visit_Medication visit_Medication = visit_MedicationService.getVisit_Medication(1L).get();
        //Then
        assertEquals(1L, visit_Medication.getId(), 0);
        assertEquals(visit, visit_Medication.getVisit());
        assertEquals(medication, visit_Medication.getMedication());
        assertEquals("dose", visit_Medication.getDose());
        assertEquals(Unit.MG, visit_Medication.getUnit());
    }

    @Test
    public void saveVisit_Medication() {
        //Given
        Visit_Medication visit_Medication1 = new Visit_Medication(1L, visit, medication, "dose", Unit.MG);

        when(visit_MedicationRepository.save(visit_Medication1)).thenReturn(visit_Medication1);
        //When
        Visit_Medication visit_Medication = visit_MedicationService.saveVisit_Medication(visit_Medication1);
        //Then
        assertEquals(1L, visit_Medication.getId(), 0);
        assertEquals(visit, visit_Medication.getVisit());
        assertEquals(medication, visit_Medication.getMedication());
        assertEquals("dose", visit_Medication.getDose());
        assertEquals(Unit.MG, visit_Medication.getUnit());
    }


    @Test
    public void deleteVisit_Medication() {
        //When
        visit_MedicationService.deleteVisit_Medication(1L);
        //Then
        Mockito.verify(visit_MedicationRepository, times(1)).deleteById(1L);
    }
}