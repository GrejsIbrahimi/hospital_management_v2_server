package com.polishealt.hospital_management_v2.controllers;

import com.polishealt.hospital_management_v2.model.Department;
import com.polishealt.hospital_management_v2.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Department entities.
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Retrieves all departments.
     * @return List of all Department objects.
     */
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    /**
     * Retrieves a department by ID.
     * @param id The ID of the Department to retrieve.
     * @return Optional containing the Department, or empty if not found.
     */
    @GetMapping("/{id}")
    public Optional<Department> getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    /**
     * Creates a new department.
     * @param department The Department object to create.
     * @return The saved Department object.
     */
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    /**
     * Deletes a department by ID.  Handles cases where the department has assigned patients.
     * @param id The ID of the Department to delete.
     * @return ResponseEntity with a success message or an error message if deletion fails.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.deleteDepartment(id);
            return ResponseEntity.ok("Department deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}