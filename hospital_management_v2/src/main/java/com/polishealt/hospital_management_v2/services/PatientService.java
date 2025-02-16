package com.polishealt.hospital_management_v2.services;

import com.polishealt.hospital_management_v2.model.AdmissionState;
import com.polishealt.hospital_management_v2.model.Department;
import com.polishealt.hospital_management_v2.model.Patient;
import com.polishealt.hospital_management_v2.repositories.AdmissionStateRepository;
import com.polishealt.hospital_management_v2.repositories.DepartmentRepository;
import com.polishealt.hospital_management_v2.repositories.PatientRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Patient entities.
 */
@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;
    private final AdmissionStateRepository admissionStateRepository; // Corrected variable name

    public PatientService(PatientRepository patientRepository, DepartmentRepository departmentRepository, AdmissionStateRepository admissionStateRepository) {
        this.patientRepository = patientRepository;
        this.departmentRepository = departmentRepository;
        this.admissionStateRepository = admissionStateRepository; // Corrected variable name
    }

    /**
     * Retrieves all patients.
     * @return List of all patients.
     */
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    /**
     * Retrieves a patient by ID.
     * @param id The ID of the patient to retrieve.
     * @return Optional containing the patient, or empty if not found.
     */
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    /**
     * Adds a new patient.
     * @param patient The patient object to add.
     * @return The saved patient object.
     */
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    /**
     * Deletes a patient by ID.
     * @param id The ID of the patient to delete.
     * @throws RuntimeException if the patient is not found.
     */
    public void deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
        } else {
            throw new RuntimeException("Patient not found");
        }
    }

    /**
     * Transfers a patient to another department and records the transfer in AdmissionState.
     * @param patientId The ID of the patient to transfer.
     * @param newDepartmentId The ID of the new department.
     * @return The updated patient object.
     * @throws RuntimeException if the patient or department is not found.
     */
    public Patient transferPatient(Long patientId, Long newDepartmentId) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        Optional<Department> optionalDepartment = departmentRepository.findById(newDepartmentId);

        if (optionalPatient.isEmpty() || optionalDepartment.isEmpty()) {
            throw new RuntimeException("Patient or Department not found");
        }

        Patient patient = optionalPatient.get();
        Department newDepartment = optionalDepartment.get();

        // Create a new admission record in AdmissionState
        AdmissionState newAdmission = new AdmissionState();
        newAdmission.setPatient(patient);
        newAdmission.setDepartment(newDepartment);
        newAdmission.setEnteringDate(LocalDateTime.now());

        admissionStateRepository.save(newAdmission); // Use the corrected variable name

        // Update the patient with the new department
        patient.setDepartment(newDepartment);
        patientRepository.save(patient);

        return patient;
    }
}
