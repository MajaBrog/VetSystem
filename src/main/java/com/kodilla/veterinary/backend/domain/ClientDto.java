package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private List<PetDto> petDtoList;

}
