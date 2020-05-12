package com.kodilla.veterinary.backend.facade;

import com.kodilla.veterinary.backend.domain.Client;
import com.kodilla.veterinary.backend.domain.Medication;
import com.kodilla.veterinary.backend.domain.Vaccination;
import com.kodilla.veterinary.backend.repository.ClientRepository;
import com.kodilla.veterinary.backend.repository.MedicationRepository;
import com.kodilla.veterinary.backend.repository.VaccinationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(FilterFacade.class);

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    MedicationRepository medicationRepository;

    @Autowired
    VaccinationRepository vaccinationRepository;

    public List<Client> filterClients(String nameFragment) throws SearchException {
        if (nameFragment.length() == 0) {
            LOGGER.error(SearchException.ERR_NAME_FRAGMENT_IS_NULL);
            throw new SearchException(SearchException.ERR_NAME_FRAGMENT_IS_NULL);
        }

        LOGGER.info("Searching for clients which last names contains: " + nameFragment);
        List<Client> listOfClientsFound = clientRepository.filterClients(nameFragment);
        if (listOfClientsFound.size() == 0) {
            LOGGER.info("No clients found where last name contains: " + nameFragment);
        }
        for (Client client : listOfClientsFound) {
            LOGGER.info("Clients that matches the criteria: " + client.getLastName());
        }
        LOGGER.info("End of searching process.");

        return listOfClientsFound;
    }

    public List<Medication> filterMedications(String nameFragment) throws SearchException {
        if (nameFragment.length() == 0) {
            LOGGER.error(SearchException.ERR_NAME_FRAGMENT_IS_NULL);
            throw new SearchException(SearchException.ERR_NAME_FRAGMENT_IS_NULL);
        }

        LOGGER.info("Searching for medications which names contains: " + nameFragment);
        List<Medication> listOfMedicationsFound = medicationRepository.filterMedications(nameFragment);
        if (listOfMedicationsFound.size() == 0) {
            LOGGER.info("No medications found where name contains: " + nameFragment);
        }
        for (Medication medication : listOfMedicationsFound) {
            LOGGER.info("Medications that matches the criteria: " + medication.getMedicationName());
        }
        LOGGER.info("End of searching process.");

        return listOfMedicationsFound;
    }

    public List<Vaccination> filterVaccinations(String nameFragment) throws SearchException {
        if (nameFragment.length() == 0) {
            LOGGER.error(SearchException.ERR_NAME_FRAGMENT_IS_NULL);
            throw new SearchException(SearchException.ERR_NAME_FRAGMENT_IS_NULL);
        }

        LOGGER.info("Searching for vaccinations which names contains: " + nameFragment);
        List<Vaccination> listOfVaccinationsFound = vaccinationRepository.filterVaccinations(nameFragment);
        if (listOfVaccinationsFound.size() == 0) {
            LOGGER.info("No vaccinations found where last name contains: " + nameFragment);
        }
        for (Vaccination vaccination : listOfVaccinationsFound) {
            LOGGER.info("Vaccinations that matches the criteria: " + vaccination.getName());
        }
        LOGGER.info("End of searching process.");

        return listOfVaccinationsFound;
    }

}
