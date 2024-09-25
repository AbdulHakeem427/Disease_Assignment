package edu.disease.asn6;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import edu.disease.asn1.exposure_constant.ExposureConstantUsingEnum;



public class ContactTraceTest {
	@Test
	public void testFindPatientZeroWithValidDirectExposure() {
        DiseaseControlManagerImpl diseaseControlManagerimp = new DiseaseControlManagerImpl(10,10);
        ContactTrace contactTrace = new ContactTrace(diseaseControlManagerimp);

        // Create patients with exposures
        Patient patient1 = createPatient(UUID.randomUUID(), "John", "Doe", createExposures(LocalDateTime.now()));
        Patient patient2 = createPatient(UUID.randomUUID(), "Jane", "Smith", createExposures(LocalDateTime.now()));

        List<Patient> patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);

        // Find patient zero
        PatientZero patientZero = contactTrace.findPatientZero(patients);

        // Validate the result
        assertNotNull(patientZero);
        assertTrue(patientZero.isExposed());
        assertNotEquals(patient2, patientZero.getPatient().get(0));
        assertNotEquals(LocalDateTime.now(), patientZero.getExposureDateTime());
    }

    @Test
    public void testFindPatientZeroWithNoValidDirectExposure() {
    	 DiseaseControlManagerImpl diseaseControlManagerimp = new DiseaseControlManagerImpl(10,10);
        ContactTrace contactTrace = new ContactTrace(diseaseControlManagerimp);

        // Create patients without valid exposures
        Patient patient1 = createPatient(UUID.randomUUID(), "Alice", "Johnson", createExposures(LocalDateTime.now(), ExposureConstantUsingEnum.INDIRECT_EXPOSURE.getExposure()));

        List<Patient> patients = new ArrayList<>();
        patients.add(patient1);

        // Find patient zero
        PatientZero patientZero = contactTrace.findPatientZero(patients);

        // Validate the result
        assertNotNull(patientZero);
        assertFalse(patientZero.isExposed());
        assertNull(patientZero.getPatient());
    }

    private List<Exposure> createExposures(LocalDateTime dateTime, String exposureType) {
        Exposure exposure = new Exposure(dateTime, UUID.randomUUID(), exposureType);

        List<Exposure> exposures = new ArrayList<>();
        exposures.add(exposure);

        return exposures;
    }

    private List<Exposure> createExposures(LocalDateTime dateTime) {
        return createExposures(dateTime, ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure());
    }

    private Patient createPatient(UUID patientId, String firstName, String lastName, List<Exposure> exposures) {
        Patient patient = new Patient(1, 1);
        patient.setPatientId(patientId);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setExposures(exposures);
        return patient;
    }
}
