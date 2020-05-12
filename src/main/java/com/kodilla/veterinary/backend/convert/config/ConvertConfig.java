package com.kodilla.veterinary.backend.convert.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ConvertConfig {
    @Value("${convert.api.endpoint.prod}")
    private String convertApiEndpoint;
    @Value("${convert.api.host}")
    private String convertApiHost;
    @Value("${convert.api.key}")
    private String convertApiKey;
}
