package com.kodilla.veterinary.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true,length = 11)
    @NotNull
    private int legalID;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Column(length =9)
    private String phoneNumber;
    @Column
    private Address address;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();

    public Client(@NotNull int legalID, @NotNull String firstName, @NotNull String lastName, String phoneNumber, Address address, String email) {
        this.legalID = legalID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }
}
