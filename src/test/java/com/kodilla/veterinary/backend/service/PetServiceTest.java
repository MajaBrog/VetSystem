package com.kodilla.veterinary.backend.service;

import com.kodilla.veterinary.backend.convert.client.ConvertClient;
import com.kodilla.veterinary.backend.domain.*;
import com.kodilla.veterinary.backend.repository.PetRepository;
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
public class PetServiceTest {
    @InjectMocks

    private PetService petService;

    @Mock
    private PetRepository petRepository;

    @Mock
    private ConvertClient convertClient;

    private Client client;

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
                .build();    }

    @Test
    public void getAllPets() {
        //Given
        Pet pet1 = Pet.builder()
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
        Pet pet2 = Pet.builder()
                .id(2L)
                .chipId("chipId")
                .name("name")
                .kind("kind")
                .visits(Arrays.asList(new Visit[]{}))
                .birthDate(LocalDate.now()).chronicDiseases_Pet(Arrays.asList(new ChronicDisease_Pet[]{}))
                .sterilised(true).dateOfSterilization(LocalDate.now())
                .aggressive(true)
                .client(client)
                .build();        List<Pet> pets=new ArrayList<>();
        pets.add(pet1);
        pets.add(pet2);

        when(petRepository.findAll()).thenReturn(pets);
        //When
        List allPets = petService.getAllPets();
        //Then
        assertEquals(2, allPets.size());
    }

    @Test
    public void getPet() {
        //Given
        Pet pet1 = Pet.builder()
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

        when(petRepository.findById(1L)).thenReturn(Optional.of(pet1));
        //When
        Pet pet = petService.getPet(1L).get();
        //Then
        assertEquals(1L, pet.getId(), 0);
        assertEquals("chipId", pet.getChipId());
        assertEquals("name", pet.getName());
        assertEquals("kind", pet.getKind());
        assertEquals(new ArrayList<>(), pet.getVisits());
        assertEquals(LocalDate.now(), pet.getBirthDate());
        assertEquals(new ArrayList<>(), pet.getChronicDiseases_Pet());
        assertTrue(pet.isSterilised());
        assertEquals(LocalDate.now(), pet.getDateOfSterilization());
        assertTrue(pet.isAggressive());
        assertEquals(client, pet.getClient());
    }

    @Test
    public void savePet() {
        //Given
        Pet pet1 = Pet.builder()
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
        when(petRepository.save(pet1)).thenReturn(pet1);
        //When
        Pet pet = petService.savePet(pet1);
        //Then
        assertEquals(1L, pet.getId(), 0);
        assertEquals("chipId", pet.getChipId());
        assertEquals("name", pet.getName());
        assertEquals("kind", pet.getKind());
        assertEquals(new ArrayList<>(), pet.getVisits());
        assertEquals(LocalDate.now() ,pet.getBirthDate());
        assertEquals(new ArrayList<>(), pet.getChronicDiseases_Pet());
        assertTrue(pet.isSterilised());
        assertEquals(LocalDate.now(), pet.getDateOfSterilization());
        assertTrue(pet.isAggressive());
        assertEquals(client, pet.getClient());
    }


    @Test
    public void deletePet() {

        //When
        petService.deletePet(1L);
        //Then
        Mockito.verify(petRepository, times(1)).deleteById(1L);
    }
}