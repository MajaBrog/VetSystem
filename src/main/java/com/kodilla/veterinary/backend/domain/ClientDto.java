package com.kodilla.veterinary.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Builder
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
    @Builder.Default
    private List<PetDto> petDtoList=new ArrayList<>();

//    public ClientDto(String legalID, String firstName, String lastName, String phoneNumber, Address address, String email) {
//        this.legalID = legalID;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phoneNumber = phoneNumber;
//        this.address = address;
//        this.email = email;
//    }
}
