package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long id;
    private String legalID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;
    private String email;
    private List<PetDto> petDtoList;

    public ClientDto(String legalID, String firstName, String lastName, String phoneNumber, Address address, String email) {
        this.legalID = legalID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }
}
