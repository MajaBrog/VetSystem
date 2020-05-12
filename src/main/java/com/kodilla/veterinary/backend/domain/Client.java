package com.kodilla.veterinary.backend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(name = "Client.filterClients",
        query = "SELECT * FROM CLIENT WHERE LAST_NAME LIKE CONCAT('%', :KEYWORD , '%')",
        resultClass = Client.class)
@Data
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true,length = 11)
    @NotNull
    private String legalID;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Column(length =9)
    private String phoneNumber;
    @Embedded
    private Address address;
    @Column(unique = true)
    private String email;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "client")
    private List<Pet> pets = new ArrayList<>();

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Client(@NotNull String legalID, @NotNull String firstName, @NotNull String lastName, String phoneNumber, Address address, String email) {
        this.legalID = legalID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }
}
