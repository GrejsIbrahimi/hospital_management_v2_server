package com.polishealt.hospital_management_v2.controllers;

import com.polishealt.hospital_management_v2.model.ClinicalData;
import com.polishealt.hospital_management_v2.services.ClinicalDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ClinicalData entities.
 */
@RestController
@RequestMapping("/clinical_data")
public class ClinicalDataController {

    private final ClinicalDataService clinicalDataService;

    public ClinicalDataController(ClinicalDataService clinicalDataService) {
        this.clinicalDataService = clinicalDataService;
    }

    /**
     * Retrieves all clinical data entries.
     * @return List of all ClinicalData objects.
     */
    @GetMapping
    public List<ClinicalData> getAllClinicalData() {
        return clinicalDataService.getAllClinicalData();
    }

    /**
     * Retrieves clinical data by ID.
     * @param id The ID of the ClinicalData to retrieve.
     * @return Optional containing the ClinicalData, or empty if not found.
     */
    @GetMapping("/{id}")
    public Optional<ClinicalData> getClinicalDataById(@PathVariable Long id) {
        return clinicalDataService.getClinicalDataById(id);
    }

    /**
     * Creates new clinical data.
     * @param clinicalData The ClinicalData object to create.
     * @return The saved ClinicalData object.
     */
    @PostMapping
    public ClinicalData createClinicalData(@RequestBody ClinicalData clinicalData) {
        return clinicalDataService.addClinicalData(clinicalData);
    }

    /**
     * Deletes clinical data by ID.
     * @param id The ID of the ClinicalData to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteClinicalData(@PathVariable Long id) {
        clinicalDataService.deleteClinicalData(id);
    }
}