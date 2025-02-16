package com.polishealt.hospital_management_v2.services;

import com.polishealt.hospital_management_v2.model.Department;
import com.polishealt.hospital_management_v2.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Department entities.
 */
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Retrieves all departments.
     * @return List of all departments.
     */
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    /**
     * Retrieves a department by ID.
     * @param id The ID of the department to retrieve.
     * @return Optional containing the department, or empty if not found.
     */
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    /**
     * Adds a new department.
     * @param department The department object to add.
     * @return The saved department object.
     */
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    /**
     * Deletes a department by ID.
     * @param id The ID of the department to delete.
     * @throws RuntimeException if the department is not found or has assigned patients.
     */
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        if (!department.getPatients().isEmpty()) {
            throw new RuntimeException("Cannot delete department because it has assigned patients.");
        }

        departmentRepository.delete(department);
    }
}
