package com.polishealt.hospital_management_v2.repositories;

import com.polishealt.hospital_management_v2.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Department entities in the database.
 * Extends JpaRepository to inherit standard CRUD operations.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}