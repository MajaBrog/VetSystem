package com.kodilla.veterinary.backend.sms.service;

import com.kodilla.veterinary.backend.sms.client.SMSClient;
import com.kodilla.veterinary.backend.sms.domain.SMSDto;
import com.kodilla.veterinary.backend.sms.domain.SMSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SMSService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    SMSClient smsClient;

    public void send(final SMSDto sms) {
        LOGGER.info("Starting SMS preparation...");
        try {
            smsClient.sendSMS(sms);
            LOGGER.info("SMS has been sent.");
        } catch (SMSException e) {
            LOGGER.error("Failed to process SMS sending: ", e.getMessage(), e);
        }
    }
}
