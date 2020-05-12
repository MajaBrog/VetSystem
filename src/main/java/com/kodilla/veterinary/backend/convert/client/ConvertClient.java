package com.kodilla.veterinary.backend.convert.client;

import com.kodilla.veterinary.backend.convert.config.ConvertConfig;
import com.kodilla.veterinary.backend.convert.domain.ConvertDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class ConvertClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertClient.class);

    @Autowired
    ConvertConfig convertConfig;
    @Autowired
    private RestTemplate restTemplate;

    public ConvertDto convert(ConvertDto convertDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(convertConfig.getConvertApiEndpoint()).build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", convertConfig.getConvertApiHost());
        headers.set("x-rapidapi-key", convertConfig.getConvertApiKey());
        headers.setContentType(APPLICATION_JSON);

        String json = "{\"from-type\":\"" + convertDto.getFromType() +
                "\",\"to-type\":\"" + convertDto.getToType() +
                "\",\"from-value\":\"" + convertDto.getFromValue() + "\"}";


        HttpEntity<String> request = new HttpEntity<String>(json, headers);

        return restTemplate.postForObject(url, request, ConvertDto.class);
    }
}
