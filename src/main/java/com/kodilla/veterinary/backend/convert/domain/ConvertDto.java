package com.kodilla.veterinary.backend.convert.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConvertDto {
    private String result;
    private boolean valid;
    private String fromValue;
    private String fromType;
    private String toType;

    public ConvertDto(String fromValue, String fromType, String toType) {
        this.fromValue = fromValue;
        this.fromType = fromType;
        this.toType = toType;
    }
}
