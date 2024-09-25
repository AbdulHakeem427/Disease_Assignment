package edu.disease.asn3;

import org.junit.Test;

import edu.disease.asn1.exposure_constant.ExposureConstantUsingEnum;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

public class DiseaseFileRepositoryTest {

    @Test
    public void testFile() {
        DiseaseControlManagerImpl diseaseControlManager = new DiseaseControlManagerImpl(10, 10);

        Disease flu = diseaseControlManager.addDisease("Flu", true);
        Disease cold = diseaseControlManager.addDisease("Cold", false);

        Patient patient1 = diseaseControlManager.addPatient("John", "Doe", 5, 5);
        Patient patient2 = diseaseControlManager.addPatient("Jane", "Smith", 5, 5);

        diseaseControlManager.addDiseaseToPatient(patient1.getPatientId(), flu.getDiseaseId());
        diseaseControlManager.addDiseaseToPatient(patient2.getPatientId(), cold.getDiseaseId());

        Exposure exposure1 = new Exposure(LocalDateTime.now(), patient1.getPatientId(), ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure());
        Exposure exposure2 = new Exposure(LocalDateTime.now(), patient2.getPatientId(), ExposureConstantUsingEnum.INDIRECT_EXPOSURE.getExposure());

        diseaseControlManager.addExposureToPatient(patient1.getPatientId(), exposure1);
        diseaseControlManager.addExposureToPatient(patient2.getPatientId(), exposure2);

        // Save data to files
        DiseaseFileRepository diseaseFileRepository = new DiseaseFileRepository();
        diseaseFileRepository.init("C:/Users/aabdu/OneDrive/Desktop/hakeem1/");

        diseaseFileRepository.save(diseaseControlManager.getDiseases(), diseaseControlManager.getPatients());

        // Load data from files
        DiseaseAndPatient loadedData = diseaseFileRepository.load();

        // Assertions
        assertNotNull("Loaded data should not be null", loadedData);
        
        // Assert the number of diseases and patients
        assertEquals("Number of diseases should match", diseaseControlManager.getDiseases().length, loadedData.getDiseases().length);
        assertEquals("Number of patients should match", diseaseControlManager.getPatients().length, loadedData.getPatients().length);

        // Assert each disease
        for (int i = 0; i < diseaseControlManager.getDiseases().length; i++) {
            assertEquals("Disease should match", diseaseControlManager.getDiseases()[i], loadedData.getDiseases()[i]);
        }

        // Assert each patient
        for (int i = 0; i < diseaseControlManager.getPatients().length; i++) {
            assertEquals("Patient should match", diseaseControlManager.getPatients()[i], loadedData.getPatients()[i]);
        }
    }
}
