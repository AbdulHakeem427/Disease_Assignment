package edu.disease.asn4;

import org.junit.jupiter.api.Test;

import edu.disease.asn1.exposure_constant.ExposureConstantUsingEnum;
import edu.disease.asn3.DiseaseControlManager;
import edu.disease.asn3.DiseaseControlManagerImpl;
import edu.disease.asn3.Exposure;
import edu.disease.asn3.Patient;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class ContactTraceTest {

    @Test
    void testNoExposures() {
        DiseaseControlManagerImpl diseaseControlManager = new DiseaseControlManagerImpl(10, 10);
        Patient patient = diseaseControlManager.addPatient("John", "Doe", 5, 5);
        ContactTrace contactTrace = new ContactTrace(diseaseControlManager);

        PatientZero patientZero = contactTrace.findPatientZero(patient);

        assertFalse(patientZero.isExposed(), "Patient Zero should not be exposed.");
        assertNull(patientZero.getPatient(), "Patient should be null.");
    }

    @Test
    void testValidExposures() {
        DiseaseControlManager diseaseControlManager = new DiseaseControlManagerImpl(10, 10);
        Patient patient1 = diseaseControlManager.addPatient("John", "Doe", 5, 5);
        Patient patient2 = diseaseControlManager.addPatient("Jane", "Smith", 5, 5);

        Exposure exposure1 = new Exposure(LocalDateTime.now(), patient1.getPatientId(), ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure());
        Exposure exposure2 = new Exposure(LocalDateTime.now(), patient2.getPatientId(), ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure());

        diseaseControlManager.addExposureToPatient(patient1.getPatientId(), exposure1);
        diseaseControlManager.addExposureToPatient(patient2.getPatientId(), exposure2);

        ContactTrace contactTrace = new ContactTrace(diseaseControlManager);

        // Test for patient1
        PatientZero patientZero1 = contactTrace.findPatientZero(patient1);
        assertTrue(patientZero1.isExposed(), "Patient Zero for patient1 should be exposed.");
        assertNotNull(patientZero1.getPatient(), "Patient for patient1 should not be null.");

        // Test for patient2
        PatientZero patientZero2 = contactTrace.findPatientZero(patient2);
        assertTrue(patientZero2.isExposed(), "Patient Zero for patient2 should be exposed.");
        assertNotNull(patientZero2.getPatient(), "Patient for patient2 should not be null.");
    }
}
