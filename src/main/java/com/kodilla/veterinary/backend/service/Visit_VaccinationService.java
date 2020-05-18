package com.kodilla.veterinary.backend.service;


import com.kodilla.veterinary.backend.controller.RecordNotFoundException;
import com.kodilla.veterinary.backend.domain.Visit_Vaccination;
import com.kodilla.veterinary.backend.repository.Visit_VaccinationRepository;
import com.kodilla.veterinary.backend.sms.domain.SMSDto;
import com.kodilla.veterinary.backend.sms.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Visit_VaccinationService {
    @Autowired
    private Visit_VaccinationRepository repository;

    @Autowired
    private SMSService smsService;

    public List<Visit_Vaccination> getAllVisit_Vaccinations() {
        return repository.findAll();
    }

    public Optional<Visit_Vaccination> getVisit_Vaccination(final Long id) {
        return repository.findById(id);
    }

    public List<Visit_Vaccination> getVisitVaccinations(Long visitId) {
        return repository.findAll().stream()
                .filter(n -> n.getVisit().getId().equals(visitId))
                .collect(Collectors.toList());
    }

    public Visit_Vaccination saveVisit_Vaccination(final Visit_Vaccination visit_Vaccination) {
        repository.save(visit_Vaccination);
        setReminderDate(visit_Vaccination.getId());
        return visit_Vaccination;
    }

    public Visit_Vaccination setReminderDate(final Long id) {
        Visit_Vaccination visit_vaccination = getVisit_Vaccination(id).orElseThrow(RecordNotFoundException::new);
        if (visit_vaccination.getVaccination().getIntervalInWeeks() != 0) {
            visit_vaccination.setRemindDate(visit_vaccination.getVisit().getDateOfVisit().plusWeeks(visit_vaccination.getVaccination().getIntervalInWeeks()));
        }
        return repository.save(visit_vaccination);
    }

    public void deleteVisit_Vaccination(final Long id) {
        repository.deleteById(id);
    }

}
