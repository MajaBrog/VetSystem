package com.kodilla.veterinary.backend.sms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMSDto {
    private String to;
    private String from;
    private String Content;
}
