package edu.disease.asn6;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import custom_exception.InvalidMaxValueException;
import edu.disease.asn1.exposure_constant.ExposureConstantUsingEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PatientTest {

    private Patient patient;

    @Before
    public void setUp() {
        patient = new Patient(5, 10);
        patient.setPatientId(UUID.randomUUID());
        patient.setFirstName("John");
        patient.setLastName("Doe");
    }

    @Test
    public void testAddDiseaseId() {
        UUID diseaseId = UUID.randomUUID();
        patient.addDiseaseId(diseaseId);
        assertTrue(patient.getDiseaseIds().contains(diseaseId));
    }

    @Test
    public void testAddExposure() {
    	LocalDateTime dateTime = LocalDateTime.now();
        UUID patientId = UUID.randomUUID();
        String exposureType = "SomeType"; // replace with a valid exposure type

        Exposure exposure = new Exposure(dateTime, patientId, exposureType);
        patient.addExposure(exposure);

        assertTrue(patient.getExposures().contains(exposure));
    }
    
    @Test
    public void testGetFirstName() {
        assertEquals("John", patient.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", patient.getLastName());
    }

    @Test
    public void testSetExposures() {
        List<Exposure> exposures = new ArrayList<>();
        exposures.add(new Exposure(LocalDateTime.now(),patient.getPatientId(),ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure()));
        exposures.add(new Exposure(LocalDateTime.now(),patient.getPatientId(),ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure()));
        
        patient.setExposures(exposures);
        
        assertEquals(exposures, patient.getExposures());
    }

    @Test
    public void testSetDiseaseIds() {
        List<UUID> diseaseIds = new ArrayList<>();
        diseaseIds.add(UUID.randomUUID());
        diseaseIds.add(UUID.randomUUID());
        
        patient.setDiseaseIds(diseaseIds);
        
        assertEquals(diseaseIds, patient.getDiseaseIds());
    }

    @Test
    public void testGetSerialversionuid() {
        assertEquals(1L, Patient.getSerialversionuid());
    }

    @Test
    public void testCompareTo() {
        Patient anotherPatient = new Patient(5, 10);
        anotherPatient.setFirstName("Alice");
        anotherPatient.setLastName("Doe");

        assertTrue(patient.compareTo(anotherPatient) > 0);
        assertTrue(anotherPatient.compareTo(patient) < 0);
    }

    @Test
    public void testHashCode() {
        Patient anotherPatient = new Patient(5, 10);
        anotherPatient.setPatientId(patient.getPatientId());

        assertEquals(patient.hashCode(), anotherPatient.hashCode());
    }

    @Test
    public void testEquals() {
        Patient anotherPatient = new Patient(5, 10);
        anotherPatient.setPatientId(patient.getPatientId());
        anotherPatient.setFirstName("Jane");

        assertTrue(patient.equals(anotherPatient));
    }

    @Test
    public void testToString() {
        String expectedToString = "Patient [patientId=" + patient.getPatientId() + ", firstName=John, lastName=Doe, exposures=[], diseaseIds=[]]";
        assertEquals(expectedToString, patient.toString());
    }

    @Test(expected = InvalidMaxValueException.class)
    public void testInvalidMaxValues() {
        new Patient(0, 10);
    }

    

}
