package com.polishealt.hospital_management_v2.controllers;

import com.polishealt.hospital_management_v2.model.Patient;
import com.polishealt.hospital_management_v2.services.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Patient entities.
 */
@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Retrieves all patients.
     * @return List of all Patient objects.
     */
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    /**
     * Retrieves a patient by ID.
     * @param id The ID of the Patient to retrieve.
     * @return Optional containing the Patient, or empty if not found.
     */
    @GetMapping("/{id}")
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    /**
     * Creates a new patient.
     * @param patient The Patient object to create.
     * @return The saved Patient object.
     */
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    /**
     * Deletes a patient by ID.
     * @param id The ID of the Patient to delete.
     */
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

    /**
     * Transfers a patient to a new department.
     * @param id The ID of the Patient to transfer.
     * @param newDepartmentId The ID of the new department.
     * @return The updated Patient object.
     */
    @PutMapping("/{id}/transfer")
    public Patient transferPatient(@PathVariable Long id, @RequestParam Long newDepartmentId) {
        return patientService.transferPatient(id, newDepartmentId);
    }
}