package com.kodilla.veterinary.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String street;
    private int houseNumber;
    private int homeNumber;
    private String city;
    private String postcode;
}
