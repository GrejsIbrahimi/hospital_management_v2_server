import com.polishealt.hospital_management_v2.model.ClinicalData;
import com.polishealt.hospital_management_v2.services.ClinicalDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ClinicalDataService.
 */
@DataJpaTest
public class ClinicalDataServiceTest {

    @Autowired
    private ClinicalDataService clinicalDataService; // We'll test this service
    @Autowired
    private TestEntityManager entityManager; // For persisting test data directly

    /**
     * Tests retrieving all clinical data.
     */
    @Test
    void getAllClinicalData_shouldReturnAllClinicalData() {
        // Arrange (Set up test data)
        ClinicalData data1 = new ClinicalData();
        data1.setObservation("Blood Pressure");
        data1.setValue("120/80");
        entityManager.persistAndFlush(data1); // Persist to the in-memory database

        ClinicalData data2 = new ClinicalData();
        data2.setObservation("Heart Rate");
        data2.setValue("72 bpm");
        entityManager.persistAndFlush(data2);

        // Act (Call the service method)
        List<ClinicalData> allData = clinicalDataService.getAllClinicalData();

        // Assert (Verify the results)
        assertEquals(2, allData.size());
        assertEquals("120/80", allData.get(0).getValue());
        assertEquals("72 bpm", allData.get(1).getValue());
    }

    /**
     * Tests retrieving clinical data by ID.
     */
    @Test
    void getClinicalDataById_shouldReturnCorrectClinicalData() {
        // Arrange
        ClinicalData data = new ClinicalData();
        data.setObservation("Temperature");
        data.setValue("98.6 F");
        entityManager.persistAndFlush(data);

        // Act
        Optional<ClinicalData> retrievedData = clinicalDataService.getClinicalDataById(data.getId());

        // Assert
        assertTrue(retrievedData.isPresent());
        assertEquals("98.6 F", retrievedData.get().getValue());
    }

    /**
     * Tests adding new clinical data.
     */
    @Test
    void addClinicalData_shouldAddClinicalData() {
        // Arrange
        ClinicalData newData = new ClinicalData();
        newData.setObservation("Oxygen Saturation");
        newData.setValue("99%");

        // Act
        ClinicalData addedData = clinicalDataService.addClinicalData(newData);

        // Assert
        assertNotNull(addedData.getId()); // Check if an ID was assigned
        assertEquals("99%", addedData.getValue());

        // Verify it's in the database:
        ClinicalData retrievedData = entityManager.find(ClinicalData.class, addedData.getId());
        assertNotNull(retrievedData);
        assertEquals("99%", retrievedData.getValue());
    }

    /**
     * Tests deleting clinical data.
     */
    @Test
    void deleteClinicalData_shouldDeleteClinicalData() {
        // Arrange
        ClinicalData data = new ClinicalData();
        data.setObservation("Blood Sugar");
        data.setValue("100 mg/dL");
        entityManager.persistAndFlush(data);

        // Act
        clinicalDataService.deleteClinicalData(data.getId());

        // Assert
        Optional<ClinicalData> deletedData = entityManager.find(ClinicalData.class, data.getId());
        assertNull(deletedData); // Should be null because it was deleted
    }

        /**
     * Tests deleting clinical data that does not exist.
     */
    @Test
    void deleteClinicalData_nonExistentId() {

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            clinicalDataService.deleteClinicalData(123L); //Non existent id
        });
    }


    // Add more test methods for other scenarios as needed...
}