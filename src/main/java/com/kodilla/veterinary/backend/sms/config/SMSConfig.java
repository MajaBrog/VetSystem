package com.kodilla.veterinary.backend.sms.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SMSConfig {
    @Value("${sms.api.endpoint.prod}")
    private String smsApiEndpoint;
    @Value("${sms.api.host}")
    private String smsApiHost;
    @Value("${sms.api.key}")
    private String smsApiKey;
}
