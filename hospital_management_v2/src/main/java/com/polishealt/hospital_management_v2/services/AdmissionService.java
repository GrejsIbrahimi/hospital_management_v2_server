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
 * Service class for managing AdmissionState entities.
 */
@Service
public class AdmissionService {

    private final AdmissionStateRepository admissionRepository;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;

    public AdmissionService(AdmissionStateRepository admissionRepository,
                          PatientRepository patientRepository,
                          DepartmentRepository departmentRepository) {
        this.admissionRepository = admissionRepository;
        this.patientRepository = patientRepository;
        this.departmentRepository = departmentRepository;
    }

    /**
     * Retrieves all admissions.
     * @return List of all admissions.
     */
    public List<AdmissionState> getAllAdmissions() {
        return admissionRepository.findAll();
    }

    /**
     * Retrieves an admission by ID.
     * @param id The ID of the admission to retrieve.
     * @return Optional containing the admission, or empty if not found.
     */
    public Optional<AdmissionState> getAdmissionById(Long id) {
        return admissionRepository.findById(id);
    }

    /**
     * Adds a new admission.  Validates patient and department existence.
     * @param admission The admission object to add.
     * @return The saved admission object.
     * @throws RuntimeException if patient or department not found.
     */
    public AdmissionState addAdmission(AdmissionState admission) {
        Patient patient = patientRepository.findById(admission.getPatient().getId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Department department = departmentRepository.findById(admission.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        admission.setPatient(patient);
        admission.setDepartment(department);

        return admissionRepository.save(admission);
    }

    /**
     * Discharges a patient (updates admission record).
     * @param id The ID of the admission to update.
     * @param reason The reason for discharge.
     * @throws RuntimeException if the admission is not found.
     */
    public void dischargePatient(Long id, String reason) {
        Optional<AdmissionState> optionalAdmission = admissionRepository.findById(id);
        if (optionalAdmission.isEmpty()) {
            throw new RuntimeException("Admission not found");
        }

        AdmissionState admission = optionalAdmission.get();
        admission.setDischarge(true);
        admission.setExitingDate(LocalDateTime.now());
        admission.setReason(reason);

        admissionRepository.save(admission);
    }

    /**
     * Updates an existing admission.
     * @param id The ID of the admission to update.
     * @param updatedAdmission The updated admission data.
     * @return The updated AdmissionState object.
     * @throws RuntimeException If the admission is not found.
     */
    public AdmissionState updateAdmission(Long id, AdmissionState updatedAdmission) {
        Optional<AdmissionState> optionalAdmission = admissionRepository.findById(id);
        if (optionalAdmission.isEmpty()) {
            throw new RuntimeException("Admission not found");
        }

        AdmissionState admission = optionalAdmission.get();

        // Update fields if not null
        if (updatedAdmission.getExitingDate() != null) {
            admission.setExitingDate(updatedAdmission.getExitingDate());
        }
        if (updatedAdmission.getCause() != null) {
            admission.setCause(updatedAdmission.getCause());
        }
        if (updatedAdmission.getReason() != null) {
            admission.setReason(updatedAdmission.getReason());
        }
        admission.setDischarge(updatedAdmission.isDischarge());

                // Update related entities if provided.
        if (updatedAdmission.getPatient() != null) {
            admission.setPatient(updatedAdmission.getPatient());
        }
        if (updatedAdmission.getDepartment() != null) {
            admission.setDepartment(updatedAdmission.getDepartment());
        }

        return admissionRepository.save(admission);
    }
}