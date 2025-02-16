import com.polishealt.hospital_management_v2.model.Patient;
import com.polishealt.hospital_management_v2.repositories.PatientRepository;
import com.polishealt.hospital_management_v2.services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


@DataJpaTest  // For testing JPA repositories
public class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TestEntityManager entityManager; // Used for setting up data

    private PatientService patientService;

    // Initialize the PatientService before each test
    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
         PatientService = new PatientService(patientRepository);
    }

    @Test
    void getAllPatients_shouldReturnAllPatients() {
        // Arrange (Set up test data directly in the database)
        Patient patient = new Patient();
        patient.setName("Test");
        patient.setLastName("Patient");

        entityManager.persistAndFlush(patient); // Persist the patient entity

        // Act
        List<Patient> patients = patientService.getAllPatients();

        // Assert
        assertEquals(1, patients.size());
        assertEquals("Test", patients.get(0).getName()); // Check the name
    }
}