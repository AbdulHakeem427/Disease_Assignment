package edu.disease.asn3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import custom_exception.FirstOrLastNameNullException;
import edu.disease.asn1.exposure_constant.ExposureConstantUsingEnum;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;


class DiseaseControlManagerImplTest {

	 private DiseaseControlManagerImpl manager;

	    @BeforeEach
	    void setUp() {
	        manager = new DiseaseControlManagerImpl(10, 10);
	    }
	    
	    @Test
	    void constructorException() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            manager = new DiseaseControlManagerImpl(-10, 10);
	        });
	    }
	    @Test
	    void constructorException2() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            manager = new DiseaseControlManagerImpl(10, -10);
	        });
	    }
	    @Test
	    void constructorException3() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            manager = new DiseaseControlManagerImpl(-10, -10);
	        });
	    }

	    @Test
	    void addInfectiousDisease() {
	        Disease disease = manager.addDisease("Flu", true);
	        assertNotNull(disease);
	        assertTrue(disease instanceof InfectiousDisease);
	        assertEquals("Flu", disease.getName());
	        assertNotNull(disease.getDiseaseId());
	    }

	    @Test
	    void addNonInfectiousDisease() {
	        Disease disease = manager.addDisease("Headache", false);
	        assertNotNull(disease);
	        assertTrue(disease instanceof NonInfectiousDisease);
	        assertEquals("Headache", disease.getName());
	        assertNotNull(disease.getDiseaseId());
	    }

	    @Test
	    void addDiseaseWithNullNameShouldThrowException() {
	        assertThrows(IllegalArgumentException.class, () -> manager.addDisease(null, true));
	    }

	    @Test
	    void addInfectiousDiseaseWhenArrayIsFullShouldThrowException() {
	        for (int i = 0; i < 10; i++) {
	            manager.addDisease("Disease" + i, true);
	        }
	        assertThrows(IllegalStateException.class, () -> manager.addDisease("OverflowDisease", true));
	    }

	    @Test
	    void addNonInfectiousDiseaseWhenArrayIsFullShouldThrowException() {
	        for (int i = 0; i < 10; i++) {
	            manager.addDisease("Disease" + i, false);
	        }
	        assertThrows(IllegalStateException.class, () -> manager.addDisease("OverflowDisease", false));
	    }

	    @Test
	    void addedDiseaseShouldBeInDiseasesArray() {
	        Disease disease = manager.addDisease("TestDisease", true);
	        assertTrue(arrayContainsDisease(manager.getDiseases(), disease));
	    }

	    private boolean arrayContainsDisease(Disease[] diseases, Disease targetDisease) {
	        for (Disease disease : diseases) {
	            if (disease != null && disease.equals(targetDisease)) {
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    @Test
	    void addPatientSuccessfully() {
	        Patient patient = manager.addPatient("John", "Doe", 3, 2);
	        assertNotNull(patient);
	        assertEquals("John", patient.getFirstName());
	        assertEquals("Doe", patient.getLastName());
	        assertNotNull(patient.getPatientId());
	    }



	    @Test
	    void addPatientWithFullArrayShouldThrowException() {
	        for (int i = 0; i < 10; i++) {
	            manager.addPatient("John" + i, "Doe" + i, 3, 2);
	        }
	        assertThrows(IllegalStateException.class, () -> manager.addPatient("Overflow", "Patient", 3, 2));
	    }

	    @Test
	    void addPatientWithNullFirstNameShouldThrowException() {
	        assertThrows(FirstOrLastNameNullException.class, () -> manager.addPatient(null, "Doe", 3, 2));
	    }

	    @Test
	    void addPatientWithNullLastNameShouldThrowException() {
	        assertThrows(FirstOrLastNameNullException.class, () -> manager.addPatient("John", null, 3, 2));
	    }

	    

	    @Test
	    void addedPatientShouldBeInPatientsArray() {
	        Patient patient = manager.addPatient("TestFirstName", "TestLastName", 3, 2);
	        assertTrue(arrayContainsPatient(manager.getPatients(), patient));
	    }

	    private boolean arrayContainsPatient(Patient[] patients, Patient targetPatient) {
	        for (Patient patient : patients) {
	            if (patient != null && patient.equals(targetPatient)) {
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    @Test
	    void addDiseaseToPatientSuccessfully() {
	        Patient patient = manager.addPatient("John", "Doe", 3, 2);
	        Disease disease = manager.addDisease("Flu", true);

	        manager.addDiseaseToPatient(patient.getPatientId(), disease.getDiseaseId());

	        assertTrue(Arrays.asList(patient.getDiseaseIds()).contains(disease.getDiseaseId()));
	    }
	    
	    @Test
	    void addExposureToPatientSuccessfully() {
	        Patient patient = manager.addPatient("Alice", "Miller", 3, 2);

	        LocalDateTime exposureDateTime = LocalDateTime.now();
	        UUID exposurePatientId = patient.getPatientId();
	        String exposureType = ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure();
	        Exposure exposure = new Exposure(exposureDateTime, exposurePatientId, exposureType);

	        manager.addExposureToPatient(patient.getPatientId(), exposure);

	        assertEquals(exposure, patient.getExposures()[0]);
	    }

	    

	    @Test
	    void getPatientSuccessfully() {
	        Patient patient = manager.addPatient("John", "Doe", 3, 2);

	        UUID patientId = patient.getPatientId();
	        assertEquals(patient, manager.getPatientById(patientId));
	    }

	   

	    @Test
	    void getDiseaseSuccessfully() {
	        Disease disease = manager.addDisease("Fever", true);

	        UUID diseaseId = disease.getDiseaseId();
	        assertEquals(disease, manager.getDisease(diseaseId));
	    }

	    //////////////////
	   

	    @Test
	    public void testFindPatientAndDiseaseById() {
	        DiseaseControlManagerImpl diseaseControlManager = new DiseaseControlManagerImpl(10, 10);

	        Disease flu = diseaseControlManager.addDisease("Flu", true);
	        Patient patient1 = diseaseControlManager.addPatient("John", "Doe", 5, 5);

	        UUID patientIdToFind = patient1.getPatientId();
	        Patient foundPatient = diseaseControlManager.getPatientById(patientIdToFind);
	        assertEquals(patient1, foundPatient, "Found patient should be the same as the added patient");

	        UUID diseaseIdToFind = flu.getDiseaseId();
	        Disease foundDisease = diseaseControlManager.getDisease(diseaseIdToFind);
	        assertEquals(flu, foundDisease, "Found disease should be the same as the added disease");
	    }
}
