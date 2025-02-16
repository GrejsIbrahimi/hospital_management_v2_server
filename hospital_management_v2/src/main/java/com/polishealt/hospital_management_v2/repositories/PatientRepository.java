package com.polishealt.hospital_management_v2.repositories;

import com.polishealt.hospital_management_v2.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Patient entities in the database.
 * Extends JpaRepository to inherit standard CRUD operations.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}