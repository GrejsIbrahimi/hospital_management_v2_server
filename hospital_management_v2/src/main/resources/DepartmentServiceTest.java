import com.polishealt.hospital_management_v2.model.Department;
import com.polishealt.hospital_management_v2.repositories.DepartmentRepository;
import com.polishealt.hospital_management_v2.services.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the DepartmentService.
 * Uses an in-memory database for testing.
 */
@DataJpaTest  // For testing JPA repositories
public class DepartmentServiceTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager; // Used for setting up data

    private DepartmentService departmentService;

    /**
     * Sets up the DepartmentService before each test.
     */
    @BeforeEach
    public void setUp() {
        departmentService = new DepartmentService(departmentRepository);
    }

    /**
     * Tests retrieving all departments.
     */
    @Test
    void getAllDepartments_shouldReturnAllDepartments() {
        // Arrange (Set up test data directly in the database)
        Department department1 = new Department();
        department1.setCode("CARDIO");
        department1.setName("Cardiology");
        entityManager.persistAndFlush(department1); // Persist the department entity

        Department department2 = new Department();
        department2.setCode("NEURO");
        department2.setName("Neurology");
        entityManager.persistAndFlush(department2); // Persist the department entity

        // Act (Call the method you're testing)
        List<Department> departments = departmentService.getAllDepartments();

        // Assert (Check the results)
        assertEquals(2, departments.size());
        assertEquals("CARDIO", departments.get(0).getCode());
        assertEquals("NEURO", departments.get(1).getCode());
    }

    /**
     * Tests adding a new department.
     */
    @Test
    void addDepartment_shouldAddDepartment() {
        // Arrange
        Department newDepartment = new Department();
        newDepartment.setCode("RADIO");
        newDepartment.setName("Radiology");

        // Act
        Department addedDepartment = departmentService.addDepartment(newDepartment);

        // Assert
        assertEquals("RADIO", addedDepartment.getCode());
        assertEquals("Radiology", addedDepartment.getName());

        // Verify that the department is actually in the database:
        Department retrievedDepartment = entityManager.find(Department.class, addedDepartment.getId());
        assertNotNull(retrievedDepartment); // Check if it exists
        assertEquals("RADIO", retrievedDepartment.getCode());
    }

    /**
     * Tests retrieving a department by ID.
     */
    @Test
    void getDepartmentById_shouldReturnCorrectDepartment() {
        // Arrange
        Department department = new Department();
        department.setCode("CARDIO");
        department.setName("Cardiology");
        entityManager.persistAndFlush(department);

        // Act
        Optional<Department> retrievedDepartment = departmentService.getDepartmentById(department.getId());

        // Assert
        assertTrue(retrievedDepartment.isPresent());
        assertEquals("CARDIO", retrievedDepartment.get().getCode());
    }


    /**
     * Tests deleting a department.
     */
    @Test
    void deleteDepartment_shouldDeleteDepartment() {
        // Arrange
        Department department = new Department();
        department.setCode("CARDIO");
        department.setName("Cardiology");
        entityManager.persistAndFlush(department);

        // Act
        departmentService.deleteDepartment(department.getId());

        // Assert
        Optional<Department> deletedDepartment = departmentRepository.findById(department.getId());
        assertTrue(deletedDepartment.isEmpty());
    }

    /**
     * Tests deleting a department that has patients assigned to it.
     * Should throw a RuntimeException.
     */
    @Test
    void deleteDepartment_withAssignedPatients_shouldThrowException() {
        // Arrange
        Department department = new Department();
        department.setCode("CARDIO");
        department.setName("Cardiology");
        entityManager.persistAndFlush(department);

        // Add a patient to the department (you'll need a Patient entity and repository)
        // ... (Code to create and persist a Patient associated with this department)

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            departmentService.deleteDepartment(department.getId());
        });
    }


    // Add more test methods for other scenarios as needed...
}
