import com.polishealt.hospital_management_v2.model.AdmissionState;
import com.polishealt.hospital_management_v2.model.Department;
import com.polishealt.hospital_management_v2.model.Patient;
import com.polishealt.hospital_management_v2.repositories.AdmissionStateRepository;
import com.polishealt.hospital_management_v2.repositories.DepartmentRepository;
import com.polishealt.hospital_management_v2.repositories.PatientRepository;
import com.polishealt.hospital_management_v2.services.AdmissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the AdmissionService.
 */
@DataJpaTest
public class AdmissionServiceTest {

    @Autowired
    private AdmissionStateRepository admissionStateRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    private AdmissionService admissionService;

    /**
     * Sets up the AdmissionService before each test.
     */
    @BeforeEach
    public void setUp() {
        admissionService = new AdmissionService(admissionStateRepository, patientRepository, departmentRepository);
    }

    /**
     * Tests retrieving all admissions.
     */
    @Test
    void getAllAdmissions_shouldReturnAllAdmissions() {
        // Arrange
        Patient patient = new Patient();
        patient.setName("Test");
        patient.setLastName("Patient");
        entityManager.persistAndFlush(patient);

        Department department = new Department();
        department.setCode("CARDIO");
        department.setName("Cardiology");
        entityManager.persistAndFlush(department);

        AdmissionState admission1 = new AdmissionState();
        admission1.setPatient(patient);
        admission1.setDepartment(department);
        admission1.setEnteringDate(LocalDateTime.now());
        entityManager.persistAndFlush(admission1);

        AdmissionState admission2 = new AdmissionState();
        admission2.setPatient(patient);
        admission2.setDepartment(department);
        admission2.setEnteringDate(LocalDateTime.now());
        entityManager.persistAndFlush(admission2);


        // Act
        List<AdmissionState> admissions = admissionService.getAllAdmissions();

        // Assert
        assertEquals(2, admissions.size());
    }

    /**
     * Tests retrieving an admission by ID.
     */
    @Test
    void getAdmissionById_shouldReturnCorrectAdmission() {
        // Arrange
        Patient patient = new Patient();
        patient.setName("Test");
        patient.setLastName("Patient");
        entityManager.persistAndFlush(patient);

        Department department = new Department();
        department.setCode("CARDIO");
        department.setName("Cardiology");
        entityManager.persistAndFlush(department);

        AdmissionState admission = new AdmissionState();
        admission.setPatient(patient);
        admission.setDepartment(department);
        admission.setEnteringDate(LocalDateTime.now());
        entityManager.persistAndFlush(admission);

        // Act
        Optional<AdmissionState> retrievedAdmission = admissionService.getAdmissionById(admission.getId());

        // Assert
        assertTrue(retrievedAdmission.isPresent());
        assertEquals(patient.getId(), retrievedAdmission.get().getPatient().getId());
    }

    /**
     * Tests adding a new admission.
     */
    @Test
    void addAdmission_should
