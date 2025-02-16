package com.polishealt.hospital_management_v2.controllers;

import com.polishealt.hospital_management_v2.model.AdmissionState;
import com.polishealt.hospital_management_v2.services.AdmissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AdmissionState entities.
 */
@RestController
@RequestMapping("/admissions")
public class AdmissionStateController {

    private final AdmissionService admissionService;

    public AdmissionStateController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    /**
     * Retrieves all admissions.
     * @return List of all AdmissionState objects.
     */
    @GetMapping
    public List<AdmissionState> getAllAdmissions() {
        return admissionService.getAllAdmissions();
    }

    /**
     * Retrieves an admission by ID.
     * @param id The ID of the AdmissionState to retrieve.
     * @return Optional containing the AdmissionState, or empty if not found.
     */
    @GetMapping("/{id}")
    public Optional<AdmissionState> getAdmissionById(@PathVariable Long id) {
        return admissionService.getAdmissionById(id);
    }

    /**
     * Adds a new admission.
     * @param admission The AdmissionState object to add.
     * @return The saved AdmissionState object.
     */
    @PostMapping
    public AdmissionState addAdmission(@RequestBody AdmissionState admission) {
        return admissionService.addAdmission(admission);
    }

    /**
     * Discharges a patient (updates admission record).
     * @param id The ID of the AdmissionState to update.
     * @param reason The reason for discharge.
     */
    @PutMapping("/{id}/discharge")
    public void dischargePatient(@PathVariable Long id, @RequestParam String reason) {
        admissionService.dischargePatient(id, reason);
    }

    /**
     * Updates an existing admission.
     * @param id The ID of the AdmissionState to update.
     * @param updatedAdmission The updated AdmissionState object.
     * @return The updated AdmissionState object.
     */
    @PutMapping("/{id}")
    public AdmissionState updateAdmission(@PathVariable Long id, @RequestBody AdmissionState updatedAdmission) {
        return admissionService.updateAdmission(id, updatedAdmission);
    }
}