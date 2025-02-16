package com.polishealt.hospital_management_v2.services;

import com.polishealt.hospital_management_v2.model.ClinicalData;
import com.polishealt.hospital_management_v2.repositories.ClinicalDataRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing ClinicalData entities.
 */
@Service
public class ClinicalDataService {

    private final ClinicalDataRepository clinicalDataRepository;

    public ClinicalDataService(ClinicalDataRepository clinicalDataRepository) {
        this.clinicalDataRepository = clinicalDataRepository;
    }

    /**
     * Retrieves all clinical data entries.
     * @return List of all clinical data.
     */
    public List<ClinicalData> getAllClinicalData() {
        return clinicalDataRepository.findAll();
    }

    /**
     * Retrieves clinical data by ID.
     * @param id The ID of the clinical data to retrieve.
     * @return Optional containing the clinical data, or empty if not found.
     */
    public Optional<ClinicalData> getClinicalDataById(Long id) {
        return clinicalDataRepository.findById(id);
    }

    /**
     * Adds new clinical data.
     * @param clinicalData The clinical data object to add.
     * @return The saved clinical data object.
     */
    public ClinicalData addClinicalData(ClinicalData clinicalData) {
        return clinicalDataRepository.save(clinicalData);
    }

    /**
     * Deletes clinical data by ID.
     * @param id The ID of the clinical data to delete.
     * @throws RuntimeException if the clinical data is not found.
     */
    public void deleteClinicalData(Long id) {
        if (clinicalDataRepository.existsById(id)) {
            clinicalDataRepository.deleteById(id);
        } else {
            throw new RuntimeException("Clinical data not found");
        }
    }
}