package com.kodilla.veterinary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long id;
    private int legalID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;
    private String email;

    public ClientDto(int legalID, String firstName, String lastName, String phoneNumber, Address address, String email) {
        this.legalID = legalID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }
}
