package com.kodilla.veterinary.backend.service;

import com.kodilla.veterinary.backend.convert.client.ConvertClient;
import com.kodilla.veterinary.backend.domain.*;
import com.kodilla.veterinary.backend.repository.ChronicDisease_PetRepository;
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
public class ChronicDisease_PetServiceTest {
    @InjectMocks

    private ChronicDisease_PetService chronicDisease_PetService;

    @Mock
    private ChronicDisease_PetRepository chronicDisease_PetRepository;

    @Mock
    private ConvertClient convertClient;

    private ChronicDisease chronicDisease;
    private Pet pet;

    @Before
    public void init() {
        Address address = new Address("street", 1, 2, "city", "postcode");
        chronicDisease = new ChronicDisease(1L, "name", Arrays.asList(new ChronicDisease_Pet[]{}));
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
                .build();
    }

    @Test
    public void getAllChronicDisease_Pets() {
        //Given
        ChronicDisease_Pet chronicDisease_Pet1 = new ChronicDisease_Pet(1L, pet, chronicDisease, LocalDate.now());
        ChronicDisease_Pet chronicDisease_Pet2 = new ChronicDisease_Pet(2L, pet, chronicDisease, LocalDate.now());
        List<ChronicDisease_Pet> chronicDisease_Pets = new ArrayList<>();
        chronicDisease_Pets.add(chronicDisease_Pet1);
        chronicDisease_Pets.add(chronicDisease_Pet2);

        when(chronicDisease_PetRepository.findAll()).thenReturn(chronicDisease_Pets);
        //When
        List allChronicDisease_Pets = chronicDisease_PetService.getAllPetChronicDiseases(1L);
        //Then
        assertEquals(2, allChronicDisease_Pets.size());
    }


    @Test
    public void saveChronicDisease_Pet() {
        //Given
        ChronicDisease_Pet chronicDisease_Pet1 = new ChronicDisease_Pet(1L, pet, chronicDisease, LocalDate.now());

        when(chronicDisease_PetRepository.save(chronicDisease_Pet1)).thenReturn(chronicDisease_Pet1);
        //When
        ChronicDisease_Pet chronicDisease_Pet = chronicDisease_PetService.saveChronicDisease_Pet(chronicDisease_Pet1);
        //Then
        assertEquals(1L, chronicDisease_Pet.getId(), 0);
        assertEquals(pet, chronicDisease_Pet.getPet());
        assertEquals(chronicDisease, chronicDisease_Pet.getChronicDisease());
        assertEquals(LocalDate.now(), chronicDisease_Pet.getDateOfDiagnosis());
    }


    @Test
    public void deleteChronicDisease_Pet() {

        //When
        chronicDisease_PetService.deleteChronicDisease_Pet(1L);
        //Then
        Mockito.verify(chronicDisease_PetRepository, times(1)).deleteById(1L);
    }
}