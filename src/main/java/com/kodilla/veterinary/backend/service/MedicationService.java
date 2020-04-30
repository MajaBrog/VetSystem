package com.kodilla.veterinary.backend.service;

import com.kodilla.veterinary.backend.convert.client.ConvertClient;
import com.kodilla.veterinary.backend.convert.doamin.ConvertDto;
import com.kodilla.veterinary.backend.domain.Medication;
import com.kodilla.veterinary.backend.domain.Unit;
import com.kodilla.veterinary.backend.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {
    @Autowired
    private MedicationRepository repository;
    @Autowired
    ConvertClient convertClient;

    public List<Medication> getAllMedications() {
        return repository.findAll();
    }

    public Optional<Medication> getMedication(final Long id) {
        return repository.findById(id);
    }

    public Medication saveMedication(final Medication medication) {
        if (medication.getUnit() != Unit.G || medication.getUnit() != Unit.L) {
            Unit unit=Unit.G;
            if (medication.getUnit() == Unit.MG) {
                unit = Unit.G;
            } else if (medication.getUnit() == Unit.ML) {
                unit = Unit.L;
            }
            ConvertDto convertDto = new ConvertDto(medication.getDosePerKg(), medication.getUnit().toString(), unit.toString());
            String calculatedDose = convertClient.convert(convertDto).getResult();
            medication.setDosePerKg(calculatedDose);
            medication.setUnit(unit);
        }
        return repository.save(medication);
    }

    public void deleteMedication(final Long id) {
        repository.deleteById(id);
    }
}

