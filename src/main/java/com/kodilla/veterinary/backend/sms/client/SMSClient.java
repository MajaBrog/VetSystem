package com.kodilla.veterinary.backend.sms.client;

import com.kodilla.veterinary.backend.sms.config.SMSConfig;
import com.kodilla.veterinary.backend.sms.domain.SMSDto;
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
public class SMSClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(SMSClient.class);

    @Autowired
    SMSConfig convertConfig;

    @Autowired
    private RestTemplate restTemplate;

    public SMSDto sendSMS(SMSDto SMSDto) {

        URI url = UriComponentsBuilder.fromHttpUrl(convertConfig.getSmsApiEndpoint()).build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", "Basic c2psYjM0NzY6WThPSmZvdXU=");
        headers.setContentType(APPLICATION_JSON);

        String json = "{\"to\":\"48" + SMSDto.getTo() +
                "\",\"content\":\"" + SMSDto.getContent() +
                "\",\"from\":\"" + SMSDto.getFrom() +
                "\",\"dlr\":\"yes\",\"dlr-method\":\"GET\",\"dlr-level\":\"2\",\"dlr-url\":\"http://yourcustompostbackurl.com\"}";

        HttpEntity<String> request = new HttpEntity<String>(json, headers);

        return restTemplate.postForObject(url, request, SMSDto.class);
    }
}
