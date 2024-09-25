package edu.disease.asn6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import custom_exception.FirstOrLastNameNullException;
import edu.disease.asn1.exposure_constant.ExposureConstantUsingEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DiseaseControlManagerImplTest {

    private DiseaseControlManagerImpl diseaseControlManager;

    @BeforeEach
    public void setUp() {
        // Initialize the DiseaseControlManagerImpl with a maximum of 3 diseases and 3 patients
        diseaseControlManager = new DiseaseControlManagerImpl(3, 3);
    }
    @Test
    public void testInvalidMaxDiseases() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DiseaseControlManagerImpl(0, 5);
        });
    }

    @Test
    public void testInvalidMaxPatients() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DiseaseControlManagerImpl(3, 0);
        });
    }

    @Test
    public void testAddDisease() {
        Disease flu = diseaseControlManager.addDisease("Flu", true);
        assertNotNull(flu);
        assertEquals("Flu", flu.getName());
        assertNotNull(flu.getDiseaseId());
        assertTrue(diseaseControlManager.getDiseases().contains(flu));
    }

    @Test
    public void testAddPatient() {
        Patient johnDoe = diseaseControlManager.addPatient("John", "Doe", 5, 5);
        assertNotNull(johnDoe);
        assertEquals("John", johnDoe.getFirstName());
        assertEquals("Doe", johnDoe.getLastName());
        assertNotNull(johnDoe.getPatientId());
        assertTrue(diseaseControlManager.getPatients().contains(johnDoe));
    }
    @Test
    public void testGetDiseaseById() {
        Disease flu = diseaseControlManager.addDisease("Flu", true);
        assertNotNull(flu);

        UUID diseaseId = flu.getDiseaseId();
        List<Disease> matchingDiseases = diseaseControlManager.getDisease(diseaseId);

        assertNotNull(matchingDiseases);
        assertEquals(1, matchingDiseases.size());
        assertEquals(flu, matchingDiseases.get(0));
    }
    @Test
    public void testAddPatientWithNullFirstName() {
        assertThrows(FirstOrLastNameNullException.class, () -> {
            diseaseControlManager.addPatient(null, "Doe", 5, 5);
        });
    }

    @Test
    public void testAddPatientWithNullLastName() {
        assertThrows(FirstOrLastNameNullException.class, () -> {
            diseaseControlManager.addPatient("John", null, 5, 5);
        });
    }

    @Test
    public void testAddDiseaseWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            diseaseControlManager.addDisease(null, true);
        });
    }

    @Test
    public void testAddDiseaseToPatientWithInvalidIds() {
        assertThrows(IllegalArgumentException.class, () -> {
            UUID invalidPatientId = UUID.randomUUID();
            UUID invalidDiseaseId = UUID.randomUUID();
            diseaseControlManager.addDiseaseToPatient(invalidPatientId, invalidDiseaseId);
        });
    }

    @Test
    public void testGetPatientById() {
        Patient johnDoe = diseaseControlManager.addPatient("John", "Doe", 5, 5);
        assertNotNull(johnDoe);

        UUID patientId = johnDoe.getPatientId();
        List<Patient> matchingPatients = diseaseControlManager.getPatientById(patientId);

        assertNotNull(matchingPatients);
        assertEquals(1, matchingPatients.size());
        assertEquals(johnDoe, matchingPatients.get(0));
    }

    @Test
    public void testAddDiseaseToPatient2() {
        Disease flu = diseaseControlManager.addDisease("Flu", true);
        Patient johnDoe = diseaseControlManager.addPatient("John", "Doe", 5, 5);

        diseaseControlManager.addDiseaseToPatient(johnDoe.getPatientId(), flu.getDiseaseId());

        List<UUID> diseaseIds = johnDoe.getDiseaseIds();
        assertNotNull(diseaseIds);
        assertEquals(1, diseaseIds.size());
        assertEquals(flu.getDiseaseId(), diseaseIds.get(0));
    }
    

    @Test
    public void testAddExposureToPatient() {
        Patient johnDoe = diseaseControlManager.addPatient("John", "Doe", 5, 5);
        Exposure exposure = new Exposure(LocalDateTime.now(),johnDoe.getPatientId(),ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure());

        diseaseControlManager.addExposureToPatient(johnDoe.getPatientId(), exposure);

        List<Exposure> exposures = johnDoe.getExposures();
        assertNotNull(exposures);
        assertEquals(1, exposures.size());
        assertEquals(exposure, exposures.get(0));
    }
    
   
   


}

