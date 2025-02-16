package com.polishealt.hospital_management_v2.repositories;

import com.polishealt.hospital_management_v2.model.AdmissionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing AdmissionState entities in the database.
 * Extends JpaRepository to inherit standard CRUD operations.
 */
@Repository
public interface AdmissionStateRepository extends JpaRepository<AdmissionState, Long> {

}