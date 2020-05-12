package com.kodilla.veterinary.backend.sms.scheduler;

import com.kodilla.veterinary.backend.domain.Visit_Vaccination;
import com.kodilla.veterinary.backend.service.Visit_VaccinationService;
import com.kodilla.veterinary.backend.sms.domain.SMSDto;
import com.kodilla.veterinary.backend.sms.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class SMSScheduler {
    @Autowired
    Visit_VaccinationService visit_vaccinationService;

    @Autowired
    SMSService smsService;


    @Transactional
    @Scheduled(cron = "0 0 9 * * *")
    //   @Scheduled(fixedDelay = 100000000)
    public void checkVaccinationInterval() {
        visit_vaccinationService.getAllVisit_Vaccinations().stream()
                .filter(n -> n.getRemindDate().equals(LocalDate.now()))
                .forEach(this::sendReminderSMS);
    }

    public void sendReminderSMS(Visit_Vaccination visit_vaccination) {

        smsService.send(new SMSDto(visit_vaccination.getVisit().getPet().getClient().getPhoneNumber(),
                "VetClinic",
                "We would like to remind you that it is time for " + visit_vaccination.getVisit().getPet().getName() +
                        " to be vaccinated against " + visit_vaccination.getVaccination().getDisease()
        ));
    }
}

