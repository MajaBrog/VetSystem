package com.kodilla.veterinary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {
    private Long id;
    private Long chipId;
    private String name;
    private String kind;
    private String birthday;
    private boolean sterilised;
    private LocalDate dateOfSterilization;
    private boolean aggressive;
    private Long clientId;

    public PetDto(Long chipId, String name, String kind, String birthday, boolean sterilised, LocalDate dateOfSterilization, boolean aggressive, Long clientId) {
        this.chipId = chipId;
        this.name = name;
        this.kind = kind;
        this.birthday = birthday;
        this.sterilised = sterilised;
        this.dateOfSterilization = dateOfSterilization;
        this.aggressive = aggressive;
        this.clientId=clientId;
    }

}
